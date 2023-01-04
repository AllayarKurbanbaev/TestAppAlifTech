package uz.akfadiler.testappaliftech.presentation.ui.screen

import android.Manifest
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.View.OnClickListener
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.akfadiler.testappaliftech.R
import uz.akfadiler.testappaliftech.data.remote.response.albums.AlbumsResponse
import uz.akfadiler.testappaliftech.data.remote.response.photos.PhotosResponse
import uz.akfadiler.testappaliftech.data.remote.response.todos.TodosResponse
import uz.akfadiler.testappaliftech.databinding.ScreenDetailBinding
import uz.akfadiler.testappaliftech.domain.model.PhotosData
import uz.akfadiler.testappaliftech.domain.model.PostsData
import uz.akfadiler.testappaliftech.domain.model.TodosData
import uz.akfadiler.testappaliftech.presentation.ui.adapter.PhotosAdapter
import uz.akfadiler.testappaliftech.presentation.ui.adapter.PostsAdapter
import uz.akfadiler.testappaliftech.presentation.ui.adapter.TodosAdapter
import uz.akfadiler.testappaliftech.presentation.viewmodel.detail.DetailViewModel
import uz.akfadiler.testappaliftech.presentation.viewmodel.detail.DetailViewModelImpl
import uz.akfadiler.testappaliftech.utils.*

@AndroidEntryPoint
class DetailScreen : Fragment(R.layout.screen_detail), OnClickListener {

    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels<DetailViewModelImpl>()
    private val navArgs: DetailScreenArgs by navArgs()
    private val postsAdapter: PostsAdapter by lazy { PostsAdapter() }
    private val photosAdapter: PhotosAdapter by lazy { PhotosAdapter() }
    private val todosAdapter: TodosAdapter by lazy { TodosAdapter() }
    private var tabSelectedId: TabLayout.Tab? = null
    private var postsList = ArrayList<PostsData>()
    private var postPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) = with(viewModel) {
        super.onCreate(savedInstanceState)
        errorLiveData.observe(this@DetailScreen, errorObserver)
        loadPostsLiveData.observe(this@DetailScreen, loadPostsObserver)
        loadAlbumsLiveData.observe(this@DetailScreen, loadAlbumsObserver)
        loadTodosLiveData.observe(this@DetailScreen, loadTodosObserver)
        loadPhotosLiveData.observe(this@DetailScreen, loadPhotosObserver)
        backPressedLiveData.observe(this@DetailScreen, backPressedObserver)
        deletePostLiveData.observe(this@DetailScreen, deleteObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(binding)
        initObservers(viewModel)
    }

    private fun initObservers(viewModel: DetailViewModel) = with(viewModel) {
        progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }

    private fun initViews(binding: ScreenDetailBinding) = with(binding) {
        backButton.setOnClickListener(this@DetailScreen)

        if (tabSelectedId != null) tabLayoutDetail.selectTab(tabSelectedId)
        else {
            recyclerViewsVisible(posts = true)
            viewModel.loadPosts(navArgs.userId)
        }

        tabLayoutDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabSelectedId = tab
                when (tabLayoutDetail.selectedTabPosition) {
                    0 -> {
                        recyclerViewsVisible(posts = true)
                        viewModel.loadPosts(navArgs.userId)
                    }
                    1 -> {
                        recyclerViewsVisible(photos = true)
                        viewModel.loadPhotos(navArgs.userId)
                    }
                    2 -> {
                        recyclerViewsVisible(todos = true)
                        viewModel.loadTodos(navArgs.userId)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Timber.d(tab.toString())
            }

        })
        val swipe = object : SwipeToDeleteCallBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                postPosition = viewHolder.absoluteAdapterPosition
                viewModel.deletePost(postsList[viewHolder.absoluteAdapterPosition].id!!)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipe)

        itemTouchHelper.attachToRecyclerView(recyclerViewPosts)
        postsAdapter.setOnItemLongClickListener {
            showDeleteDialog(it)
        }

        photosAdapter.setOnDownloadClickListener {
            it.url?.let { url ->
                downloadImage(url)
            }
        }
    }

    private val progressObserver = Observer<Boolean> {
        if (it) binding.progress.visible()
        else binding.progress.gone()
    }

    private val deleteObserver = Observer<Unit> {
        postPosition?.let {
            postsList.removeAt(it)
            postsAdapter.notifyItemRemoved(it)
        }
        postsAdapter.submitList(postsList)
        snackMessage("Delete success!")
    }

    private val backPressedObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

    private val loadPostsObserver = Observer<List<PostsData>> {
        binding.recyclerViewPosts.adapter = postsAdapter
        postsList = it as ArrayList<PostsData>
        postsAdapter.submitList(it)
    }
    private val loadPhotosObserver = Observer<List<PhotosData>> {
        binding.recyclerViewPhotos.adapter = photosAdapter
        photosAdapter.submitList(it)
    }

    private val loadTodosObserver = Observer<List<TodosData>> {
        binding.recyclerViewTodos.adapter = todosAdapter
        todosAdapter.submitList(it)
    }
    private val loadAlbumsObserver = Observer<List<AlbumsResponse>> {

    }

    private val errorObserver = Observer<String> {
        snackMessage(it)
    }

    override fun onClick(view: View?) = with(binding) {
        when (view) {
            backButton -> viewModel.onBackPressed()
        }
    }

    private fun showDeleteDialog(pos: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle(getString(R.string.delete_item))
        alertDialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
            postPosition = pos
            viewModel.deletePost(pos)
        }
        alertDialog.setNegativeButton(getString(R.string.cancel)) { _, _ ->

        }
        alertDialog.show()
    }

    private fun recyclerViewsVisible(
        posts: Boolean = false, photos: Boolean = false, todos: Boolean = false
    ) {
        binding.recyclerViewPosts.isVisible = posts
        binding.recyclerViewPhotos.isVisible = photos
        binding.recyclerViewTodos.isVisible = todos
    }

    private fun downloadImage(url: String) {
        requireActivity().checkPermissions(arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ), {
            val request = DownloadManager.Request(Uri.parse(url))
            request.addRequestHeader("User-Agent", "your-user-agent")
            request.setTitle(requireContext().getString(R.string.image_download))
            request.setDescription(requireContext().getString(R.string.downloading))
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            val nameOfFile: String =
                URLUtil.guessFileName(url, null, MimeTypeMap.getFileExtensionFromUrl(url))
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS, nameOfFile
            )
            val manager =
                requireActivity().baseContext?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
        }, {
            showToast(getString(R.string.cancel))
        })
    }
}