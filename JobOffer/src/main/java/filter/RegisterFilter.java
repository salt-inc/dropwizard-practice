package filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response.Status;


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
		
		// サーバーエラー発生時に表示するメッセージを設定する
		if (responseContext.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			responseContext.setEntity("サーバーエラーが発生しました");
		}
		
	}

}
