package org.jonasmkarlsson.generatedata.test.column;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jonasmkarlsson.generatedata.Constants;
import org.jonasmkarlsson.generatedata.column.Avatarurl;
import org.junit.Test;

public class AvatarsUrlTest {

    @Test
    public void testAvatarUrl() {
        final int noOfDefautAvatarUrls = 1;
        Avatarurl avatarUrl = new Avatarurl(Integer.toString(noOfDefautAvatarUrls));
        String generatedValue = avatarUrl.generate();
        assertNotNull(generatedValue);
        assertNotNull(generatedValue.contains(Constants.AVATAR_URL));
        assertEquals(noOfDefautAvatarUrls, avatarUrl.getAvatarUrlDataMap().size());
    }

    @Test
    public void testAvatarUrlNotIntParameter() {
        Avatarurl avatarUrl = new Avatarurl("A");
        String generatedValue = avatarUrl.generate();
        assertNotNull(generatedValue);
        assertNotNull(generatedValue.contains(Constants.AVATAR_URL));
        assertEquals(Constants.DEFAULT_NUMBER_OF_AVATAR_URLS, avatarUrl.getAvatarUrlDataMap().size());
    }

}
