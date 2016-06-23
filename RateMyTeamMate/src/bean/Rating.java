package bean;

import java.util.ArrayList;

public class Rating {
	
		private int id;
		private String courseCode;
	    private String helpfulness;
	    private String knowledgeable;
	    private String leadership;
	    private String teamParticipation;
	    private ArrayList<String> tag;
	    private String comment;
	    private String average;

	    public Rating() {
		// TODO Auto-generated constructor stub
	    }

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

		public Rating(int id, String courseCode, String helpfulness,
				String knowledgeable, String leadership,
				String teamParticipation, ArrayList<String> tag, String comment,String average) {
			super();
			this.id = id;
			this.courseCode = courseCode;
			this.helpfulness = helpfulness;
			this.knowledgeable = knowledgeable;
			this.leadership = leadership;
			this.teamParticipation = teamParticipation;
			this.tag = tag;
			this.comment = comment;
			this.average=average;
		}

		

		public String getAverage() {
			return average;
		}

		public void setAverage(String average) {
			this.average = average;
		}

		public String getCourseCode() {
			return courseCode;
		}

		public void setCourseCode(String courseCode) {
			this.courseCode = courseCode;
		}

		public String getHelpfulness() {
			return helpfulness;
		}

		public void setHelpfulness(String helpfulness) {
			this.helpfulness = helpfulness;
		}

		public String getKnowledgeable() {
			return knowledgeable;
		}

		public void setKnowledgeable(String knowledgeable) {
			this.knowledgeable = knowledgeable;
		}

		public String getLeadership() {
			return leadership;
		}

		public void setLeadership(String leadership) {
			this.leadership = leadership;
		}

		public String getTeamParticipation() {
			return teamParticipation;
		}

		public void setTeamParticipation(String teamParticipation) {
			this.teamParticipation = teamParticipation;
		}

		

		public ArrayList<String> getTag() {
			return tag;
		}



		public void setTag(ArrayList<String> tag) {
			this.tag = tag;
		}



		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((comment == null) ? 0 : comment.hashCode());
			result = prime * result
					+ ((courseCode == null) ? 0 : courseCode.hashCode());
			result = prime * result
					+ ((helpfulness == null) ? 0 : helpfulness.hashCode());
			result = prime * result
					+ ((knowledgeable == null) ? 0 : knowledgeable.hashCode());
			result = prime * result
					+ ((leadership == null) ? 0 : leadership.hashCode());
			result = prime * result + ((tag == null) ? 0 : tag.hashCode());
			result = prime
					* result
					+ ((teamParticipation == null) ? 0 : teamParticipation
							.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Rating other = (Rating) obj;
			if (comment == null) {
				if (other.comment != null)
					return false;
			} else if (!comment.equals(other.comment))
				return false;
			if (courseCode == null) {
				if (other.courseCode != null)
					return false;
			} else if (!courseCode.equals(other.courseCode))
				return false;
			if (helpfulness == null) {
				if (other.helpfulness != null)
					return false;
			} else if (!helpfulness.equals(other.helpfulness))
				return false;
			if (knowledgeable == null) {
				if (other.knowledgeable != null)
					return false;
			} else if (!knowledgeable.equals(other.knowledgeable))
				return false;
			if (leadership == null) {
				if (other.leadership != null)
					return false;
			} else if (!leadership.equals(other.leadership))
				return false;
			if (tag == null) {
				if (other.tag != null)
					return false;
			} else if (!tag.equals(other.tag))
				return false;
			if (teamParticipation == null) {
				if (other.teamParticipation != null)
					return false;
			} else if (!teamParticipation.equals(other.teamParticipation))
				return false;
			return true;
		}
	    
	    

}
