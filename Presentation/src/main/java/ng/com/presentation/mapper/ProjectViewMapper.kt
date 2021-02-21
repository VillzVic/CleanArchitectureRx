package ng.com.presentation.mapper

import ng.com.domain.model.Project
import ng.com.presentation.model.ProjectView
import javax.inject.Inject

open class ProjectViewMapper @Inject constructor(): Mapper<Project,ProjectView> {

    override fun mapToView(type: Project): ProjectView {
        return ProjectView(
            type.id, type.name, type.fullName, type.starCount, type.dateCreated, type.ownerName, type.ownerAvatar, type.isBookmarked
        )
    }

}