package org.jonasmkarlsson.generatedata.column;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.jonasmkarlsson.generatedata.Constants;

/**
 * Works by generating a Avatar URL accessing service located at http://avatars.adorable.io. By adding parameters to a URL API, a different avatar are created,
 * only based on the suffix.
 */

public class Avatarurl extends AbstractColumn {

	private static final Random RANDOM = new Random();
	private static final Logger LOGGER = Logger.getLogger(Avatarurl.class);

	private Map<Integer, String> avatarUrlDataMap = new HashMap<Integer, String>();
	private int pixelSize = 100;

	public Avatarurl(final String parameter) {
		super(parameter);
		initAvatarUrlData();
	}

	public Avatarurl(final int pixelSize, final String parameter) {
		super(parameter);
		this.pixelSize = pixelSize;
		initAvatarUrlData();
	}

	@Override
	public String generate() {
		int randomInt = RANDOM.nextInt(avatarUrlDataMap.size());
		return avatarUrlDataMap.get(randomInt);
	}

	private void initAvatarUrlData() {
		// Find out if parameter are a valid integer, else, set default value.
		int parameterInteger = Constants.DEFAULT_NUMBER_OF_AVATAR_URLS;
		try {
			parameterInteger = Integer.parseInt(parameter);
		} catch (NumberFormatException nfe) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Could not convert value '" + parameter + "' to an int. Using default value '" + Constants.DEFAULT_NUMBER_OF_AVATAR_URLS + "'.");
			}
		}

		// Populate avatarUrlDataMap with X number of values.
		for (int i = 0; i < parameterInteger; i++) {
			// Create an Avatar URL using the base URL and a sequence of random characters and numbers
			String avatarUrl = Constants.AVATAR_URL + "/" + this.pixelSize + "/" + "" + new Sequence("[a-z0-9]{15}").generate() + ".png";
			// Put URL link in map.
			avatarUrlDataMap.put(i, avatarUrl);
		}
	}

	/**
	 * @return the avatarUrlDataMap
	 */
	public Map<Integer, String> getAvatarUrlDataMap() {
		return avatarUrlDataMap;
	}

	/**
	 * @return the pixelSize
	 */
	public int getPixelSize() {
		return pixelSize;
	}

	/**
	 * @param pixelSize the pixelSize to set
	 */
	public void setPixelSize(int pixelSize) {
		this.pixelSize = pixelSize;
	}

}