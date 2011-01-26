package net.blogjava.frankiegao123.jpa.query;

public class Sierpinski {
    
    final static char[][] T = { {' ', '/', '\\', ' '}, {'/', '_', '_', '\\'} };

    public static void main(String[] args) {
        char[][] chs = sierpinski(3);
        print(chs);
    }
    
    private static char[][] sierpinski(int n) { 
        char[][] canvas = init(n);        
        int x = 1 << (n - 2);
        if(x < 0) {
            x = 0;
        }
        int y = canvas.length - 4;
        int m = canvas[0].length / 2 - 4;
        if(m < 0) {
            m = 0;
        }
        fillTriangle(canvas, 0, m);
        for(int i = 0; i < x; i++) {
            fillTriangle(canvas, y, i * 8);
        }
        return canvas;
    }
    
    private static char[][] init(int n) {
        char[][] canvas = new char[1 << n][1 << (n + 1)];
        for(int i = 0; i < canvas.length; i++) {
            for(int j = 0; j < canvas[i].length; j++) {
                canvas[i][j] = ' ';
            }
        }
        return canvas;
    }
    
    private static void fillTriangle(char[][] canvas, int y, int x) {
        drawTriangle(canvas, y, x + 2);
        drawTriangle(canvas, y + 2, x);
        drawTriangle(canvas, y + 2, x + 4);
    }
    
    private static void drawTriangle(char[][] canvas, int row, int col) {
        for(int i = 0; i < T.length; i++) {
            for(int j = 0; j < T[i].length; j++) {
                canvas[row + i][col + j] = T[i][j];
            }
        }        
    }
    
    private static void print(char[][] chs) {
        for(int i = 0; i < chs.length; i++) {
            for(int j = 0; j < chs[i].length; j++) {
                System.out.print(chs[i][j]);
            }
            System.out.println();
        }
    }
}
