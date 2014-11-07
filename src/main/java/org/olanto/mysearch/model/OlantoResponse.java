package org.olanto.mysearch.model;

import java.io.Serializable;

public class OlantoResponse implements Serializable {

	private static final long serialVersionUID = 5992209906280783120L;

	private ResponseData data;

	public ResponseData getData() {
		return data;
	}

	public void setData(ResponseData data) {
		this.data = data;
	}
	
	
	
	public class ResponseData implements Serializable {

		private static final long serialVersionUID = -4757952863506340026L;
		
		private boolean isError;
		private String message;
		private String translatedText;
		public boolean isError() {
			return isError;
		}
		public void setError(boolean isError) {
			this.isError = isError;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getTranslatedText() {
			return translatedText;
		}
		public void setTranslatedText(String translatedText) {
			this.translatedText = translatedText;
		}
		@Override
		public String toString() {
			return " [isError='" + isError + "', message='" + message
					+ "', translatedText='" + translatedText + "']";
		}
		
		
	}



	@Override
	public String toString() {
		return "OlantoResponse [data=" + data.toString() + "]";
	}
}
