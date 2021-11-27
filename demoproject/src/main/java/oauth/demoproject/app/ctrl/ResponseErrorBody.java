package oauth.demoproject.app.ctrl;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseErrorBody {

    private String type;
    private String title;
    private String detail;
    private String instance;

    public static ResponseErrorBody validErrResBuild(String title) {
        return ResponseErrorBody.builder()
                .type("Validation Error")
                .title(title)
                .detail("about brank")
                .instance("about brank")
                .build();
    }

    public static ResponseErrorBody badReqErrResBuild(String detail) {
        return ResponseErrorBody.builder()
                .type("Bad Request")
                .title("リクエスト内容を確認してください。")
                .detail(detail)
                .instance("about brank")
                .build();
    }

    public static ResponseErrorBody intrnlServErrResBuild(String detail) {
        return ResponseErrorBody.builder()
                .type("Internal Server Error")
                .title("サーバ内エラー")
                .detail(detail)
                .instance("about brank")
                .build();
    }

    public static ResponseErrorBody serviceUnavErrResBuild(String detail) {
        return ResponseErrorBody.builder()
                .type("Service Unavailable")
                .title("サーバ停止中")
                .detail(detail)
                .instance("about brank")
                .build();
    }
}
