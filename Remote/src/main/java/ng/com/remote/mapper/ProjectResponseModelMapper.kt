package ng.com.remote.mapper

import ng.com.data.model.ProjectEntity
import ng.com.remote.model.ProjectModel
import javax.inject.Inject

open class ProjectResponseModelMapper @Inject constructor(): ModelMapper<ProjectModel, ProjectEntity>{

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName,
            model.starCount.toString(), model.dateCreated, model.owner.ownerName,
            model.owner.ownerAvatar, false)
    }

}