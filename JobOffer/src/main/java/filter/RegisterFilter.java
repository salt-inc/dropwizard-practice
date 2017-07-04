package filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * 登録時のレスポンスフィルタークラス
 * 
 * @author Kazushige Yamaguchi
 *
 */
public class RegisterFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		
		if (responseContext.getStatus() == 500) {
			responseContext.setEntity("サーバーエラーが発生しました");
		}
		
	}

}
