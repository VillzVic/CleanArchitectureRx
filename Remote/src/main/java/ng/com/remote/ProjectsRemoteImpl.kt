package ng.com.remote

import io.reactivex.Observable
import ng.com.data.model.ProjectEntity
import ng.com.data.repository.ProjectsRemote
import ng.com.remote.mapper.ProjectResponseModelMapper
import ng.com.remote.service.GithubTrendingService
import javax.inject.Inject

open class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectResponseModelMapper
):ProjectsRemote{

    override fun getprojects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map {
                it.items.map {
                    mapper.mapFromModel(it)
                }
            }
    }

}