package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Scores_Chapter10 {
    public static void main(String[] args) {
        // データベース接続情報
        String url = "jdbc:mysql://localhost:3306/challenge_java";
        String user = "root";
        String password = "Nobumaru@@918"; 

        try {
            // (1) データベースに接続する
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("データベース接続成功：" + conn);

            // (2) SQLクエリを準備する
            Statement stmt = conn.createStatement();

            // (3) 点数データを更新する（武者小路勇気さんの点数を更新）
            System.out.println("レコード更新を実行します");
            String updateSql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5";
            int updatedRows = stmt.executeUpdate(updateSql);
            System.out.println(updatedRows + "件のレコードが更新されました");

            // (4) 点数順に並べる（数学・英語の点数が高い順に並べ替え）
            System.out.println("数学・英語の点数が高い順に並べ替えました");
            String selectSql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC";
            ResultSet rs = stmt.executeQuery(selectSql);

            // (5) 並べ替え結果を表示する
            int count = 1;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int scoreMath = rs.getInt("score_math");
                int scoreEnglish = rs.getInt("score_english");
                System.out.println(count + "件目：生徒ID=" + id + "／氏名=" + name + "／数学=" + scoreMath + "／英語=" + scoreEnglish);
                count++;
            }

            // リソースを閉じる
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}