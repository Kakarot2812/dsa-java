import java.util.*;

public class FloodFill {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int m  = image.length;
            int n = image[0].length;
            int[][] ans = image;
            boolean[][] vis = new boolean[m][n];
            int oldColor = image[sr][sc];
            dfs(sr,sc,newColor,oldColor,vis,image,ans);
            return ans;
        }
        void dfs(int row,int col,int newColor,int oldColor,boolean[][] vis,int[][] image,int[][] ans){
            vis[row][col] = true;
            ans[row][col] = newColor;
            int[] dr = {-1,0,1,0};
            int[] dc = {0,1,0,-1};
            for(int i =0;i < 4;i++){
                int nrow = row + dr[i];
                int ncol = col + dc[i];
                if(nrow>=0 && nrow < image.length && ncol>=0 && ncol<image[0].length && vis[nrow][ncol] == false && image[nrow][ncol] == oldColor){
                    dfs(nrow,ncol,newColor,oldColor,vis,image,ans);
                }
            }
        }
    }
