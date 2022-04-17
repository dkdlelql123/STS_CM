package  com.nyj.exam.demo.util;

import java.net.URLEncoder;

import com.nyj.exam.demo.vo.Rq;

public class Util {

	public static boolean empty(Object obj) {
		// 값이 비어있는 경우 true
		if(obj == null) {
			return true;
		}
		
		if(obj instanceof String == false) {
			return true;
		}
		
		String str = (String) obj;
		return  str.trim().length() == 0; 
		
	}

	public static String f(String format, Object... objs) {
		return String.format(format, objs);
	}
	
	public static String jsHistoryBack(String msg) {
		if (msg == null) {
			msg = "";
		}

		return Util.f("""
				<script>
				const msg = '%s'.trim();
				if ( msg.length > 0 ) {
				    alert(msg);
				}
				history.back();
				</script>
				""", msg);
	}

	public static String jsReplace(String msg, String uri) {
		if (msg == null) {
			msg = "";
		}

		if (uri == null) {
			uri = "";
		}

		return Util.f("""
				<script>
				const msg = '%s'.trim();
				if ( msg.length > 0 ) {
				    alert(msg);
				}
				location.replace('%s');
				</script>
				""", msg, uri);
	}
	

	public static String getUriEncoded(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			return str;
		}
	}
	
}
