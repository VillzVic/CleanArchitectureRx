package ng.com.cache.mapper

import ng.com.cache.model.CachedProject
import ng.com.data.model.ProjectEntity
import javax.inject.Inject

open class CacheProjectMapper @Inject constructor() : CacheMapper<CachedProject,  ProjectEntity>{
    override fun mapFromCache(type: CachedProject): ProjectEntity {
        return  ProjectEntity(
            type.id,type.name, type.fullName, type.starCount, type.dateCreated, type.ownerName,  type.ownerAvatar, type.isBookmarked
        )
    }

    override fun mapToCache(type: ProjectEntity): CachedProject {
        return CachedProject(
            type.id,type.name, type.fullName, type.starCount, type.dateCreated, type.ownerName,  type.ownerAvatar, type.isBookmarked
        )
    }

}