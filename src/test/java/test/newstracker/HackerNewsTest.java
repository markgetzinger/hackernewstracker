package test.newstracker;

import com.newstracker.top.HackerNews;
import com.newstracker.top.TrackerOperations;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

public class HackerNewsTest {
    private TrackerOperations trackerOperations = new TrackerOperations();

    @Test
    public void trackerOperationsTest(){
        List<Integer> idList = trackerOperations.getTopIds(11);

        assertThat(idList, hasSize(11));
    }

    @Test
    public void getStoryTest(){
        HackerNews story = trackerOperations.getStory(19911715);

        assertTrue(story.getId() == 19911715);
        assertEquals(story.getTitle(), "CPU.fail");
        assertEquals(story.getBy(), "razer6");
    }

    @Test
    public void getCommentTest(){
        HackerNews comment = trackerOperations.getStory(19912128);

        assertTrue(comment.getId() == 19912128);
        assertEquals(comment.getBy(), "thro_away_n");
        assertEquals(comment.getType(), "comment");
        assertNull(comment.getTitle());
    }
}
