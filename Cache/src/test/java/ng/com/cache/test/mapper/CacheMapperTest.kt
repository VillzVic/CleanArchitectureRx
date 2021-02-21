package ng.com.cache.test.mapper

import ng.com.cache.mapper.CacheProjectMapper
import ng.com.cache.model.CachedProject
import ng.com.cache.test.factory.ProjectDataFactory
import ng.com.data.model.ProjectEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CacheProjectMapperTest {

    val mapper = CacheProjectMapper()

    @Test
    fun mapFromCacheMapData() {
        val model = ProjectDataFactory.makeCachedProject()
        val entity = mapper.mapFromCache(model)

        assertEqualsValue(model, entity)
    }

    @Test
    fun mapToCacheData(){
        val entity = ProjectDataFactory.makeProjectEntity()
        val model = mapper.mapToCache(entity)

        assertEqualsValue(model, entity)
    }

    private fun assertEqualsValue(model: CachedProject, entity: ProjectEntity) {
        assertEquals(model.id, entity.id)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.name, entity.name)
        assertEquals(model.ownerName, entity.ownerName)
        assertEquals(model.ownerAvatar, entity.ownerAvatar)
        assertEquals(model.starCount, entity.starCount)
        assertEquals(model.isBookmarked, entity.isBookmarked)
    }

}