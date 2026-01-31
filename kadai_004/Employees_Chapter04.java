package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {
    public static void main(String[] args) {
        // データベース接続情報
        String url = "jdbc:mysql://localhost:3306/challenge_java";
        String user = "root";
        String password = "Nobumaru@@918"; // ← ここを自分のパスワードに変更

        // employeesテーブル作成用のSQL
        String createTableSQL = "CREATE TABLE employees ("
                + "id INT(11) NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(60) NOT NULL, "
                + "email VARCHAR(255) NOT NULL, "
                + "age INT(11), "
                + "address VARCHAR(255), "
                + "PRIMARY KEY (id)"
                + ")";

        // データベース接続とテーブル作成
        try {
            // JDBCドライバの読み込み
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースに接続
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("データベース接続成功：" + conn);

            // テーブル作成を実行
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(createTableSQL);
            System.out.println("社員テーブルを作成しました:更新レコード数=" + result);

            // リソースを閉じる
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバが見つかりません");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("データベースエラーが発生しました");
            e.printStackTrace();
        }
    }
}