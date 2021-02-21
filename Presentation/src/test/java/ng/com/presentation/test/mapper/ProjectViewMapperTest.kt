package ng.com.presentation.test.mapper

import junit.framework.Assert.assertEquals
import ng.com.presentation.mapper.ProjectViewMapper
import ng.com.presentation.test.factory.DataFactory
import ng.com.presentation.test.factory.ProjectFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectViewMapperTest {

    private val mapper = ProjectViewMapper()

    @Test
    fun mapToViewmapData() {
        val project = ProjectFactory.makeProject()
        val projectView = mapper.mapToView(project)

        assertEquals(project.id, projectView.id)
        assertEquals(project.dateCreated, projectView.dateCreated)
        assertEquals(project.fullName, projectView.fullname)
        assertEquals(project.isBookmarked, projectView.isBookmarked)
        assertEquals(project.name, projectView.name)
        assertEquals(project.ownerName, projectView.ownerName)
        assertEquals(project.ownerAvatar, projectView.ownerAvatar)
        assertEquals(project.starCount, projectView.starCount)
    }


}