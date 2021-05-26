package com.dicoding.sample.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.sample.academy.R
import com.dicoding.sample.academy.data.entity.CourseEntity
import com.dicoding.sample.academy.databinding.ActivityDetailCourseBinding
import com.dicoding.sample.academy.databinding.ContentDetailCourseBinding
import com.dicoding.sample.academy.ui.reader.CourseReaderActivity
import com.dicoding.sample.academy.viewModel.ViewModelFactory
import com.dicoding.sample.academy.vo.Status

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var detailContentBinding: ContentDetailCourseBinding
    private lateinit var activityDetailCourseBinding: ActivityDetailCourseBinding
    private lateinit var viewModel: DetailCourseViewModel

    private var menu: Menu? = null

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       activityDetailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailCourseBinding.detailContent

        setContentView(activityDetailCourseBinding.root)
        setSupportActionBar(activityDetailCourseBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailAdapter = DetailCourseAdapter()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailCourseViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {

                viewModel.setSelectedCourse(courseId)

                viewModel.courseModule.observe(this){courseWithModuleResource ->
                    if (courseWithModuleResource != null){
                        when(courseWithModuleResource.status){
                            Status.LOADING ->{
                                activityDetailCourseBinding.detailContent.progressBar.visibility = View.VISIBLE
                                activityDetailCourseBinding.detailContent.content.visibility = View.INVISIBLE
                            }

                            Status.SUCCESS -> {
                                if (courseWithModuleResource.data != null){
                                    activityDetailCourseBinding.detailContent.progressBar.visibility = View.GONE
                                    activityDetailCourseBinding.detailContent.content.visibility = View.VISIBLE

                                    detailAdapter.setModules(courseWithModuleResource.data.mModules)
                                    detailAdapter.notifyDataSetChanged()
                                    populateCourse(courseWithModuleResource.data.mCourse)
                                }
                            }

                            Status.ERROR -> {
                                activityDetailCourseBinding.detailContent.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                }

                with(detailContentBinding.rvModule){
                    hasFixedSize()
                    adapter = detailAdapter
                    layoutManager = LinearLayoutManager(this@DetailCourseActivity)
                    val dividerItemDecoration = DividerItemDecoration(this@DetailCourseActivity, DividerItemDecoration.VERTICAL)
                    addItemDecoration(dividerItemDecoration)
                }
            }
        }
    }

    private fun populateCourse(courseEntity: CourseEntity) {
        detailContentBinding.textTitle.text = courseEntity.title
        detailContentBinding.textDescription.text = courseEntity.description
        detailContentBinding.textDate.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)

        detailContentBinding.btnStart.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        viewModel.courseModule.observe(this){courseWithModule ->
            if (courseWithModule != null){
                when(courseWithModule.status){
                    Status.LOADING -> activityDetailCourseBinding.detailContent.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        if (courseWithModule.data != null){
                            activityDetailCourseBinding.detailContent.progressBar.visibility = View.GONE
                            val state = courseWithModule.data.mCourse.bookmarked
                            setBookmarkState(state)
                        }
                    }
                    Status.ERROR -> {
                        activityDetailCourseBinding.detailContent.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark){
            viewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean){
        if(menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state){
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white)
        } else{
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }
    }
}