package ng.com.cache.dao

import androidx.room.*
import io.reactivex.Flowable
import ng.com.cache.db.ProjectConstants
import ng.com.cache.db.ProjectConstants.DELETE_PROJECTS
import ng.com.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import ng.com.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import ng.com.cache.model.CachedProject

@Dao
abstract class CachedProjectsDao {

    @Query(ProjectConstants.QUERY_PROJECTS)
    abstract fun getProjects() : Flowable<List<CachedProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProjects(projects: List<CachedProject>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean,
                                   projectId: String)

}