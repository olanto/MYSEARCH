package org.olanto.mysearch.model;

import java.io.Serializable;

/**
 * http://srv2.olanto.org/R1/translate/query?key=CORPONU&source=fr&target=en&q=bonjour
 * @author mpneurones
 *
 */
public class OlantoQueryParameter implements Serializable {

	private static final long serialVersionUID = -2569458074197063725L;
	
	public enum DEBUG { YES, NO }
	
	private String key;
	private String source;
	private String target;
	private String q;
	private DEBUG debug;
		
	public String getKey() {
		
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public DEBUG getDebug() {
		return debug;
	}
	public void setDebug(DEBUG debug) {
		this.debug = debug;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((q == null) ? 0 : q.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		OlantoQueryParameter other = (OlantoQueryParameter) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (q == null) {
			if (other.q != null)
				return false;
		} else if (!q.equals(other.q))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "QueryParameter [key=" + key + ", source=" + source
				+ ", target=" + target + ", q=" + q + ", debug=" + debug + "]";
	}
	
	public String toQueryUrl() {
		return "key="+key+"&source="+source+"&target="+target+"&q="+q+(debug==null?"":"&debug="+debug);
	}
	
}
