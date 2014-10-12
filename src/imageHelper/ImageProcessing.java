package imageHelper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageProcessing {

    private ArrayList koordObjek;
    private BufferedImage realImage;
    private BufferedImage grayImage;
    private BufferedImage binaryImage;
    private BufferedImage maxImage;
    private BufferedImage DiameterLineImage;
    private BufferedImage negativeImage;
    private BufferedImage logTransformationImage;
    private BufferedImage powerLawImage;
    private BufferedImage bitPlaneImage;
    private int tinggiCitra;
    private int lebarCitra;
    private double diameterObjek;
    private double xKanan;
    private double xKiri;
    private double meanR;
    private double meanG;
    private double meanB;
    private double meanRGB;
    private double varMeanRGB;
    private int maxGray;

    public ImageProcessing() {
        this.realImage = null;
        this.grayImage = null;
        this.binaryImage = null;
        this.maxImage = null;
        this.negativeImage = null;
        this.logTransformationImage = null;
        this.powerLawImage = null;
        this.bitPlaneImage = null;
        this.meanR = 0;
        this.meanG = 0;
        this.meanB = 0;
        this.koordObjek = new ArrayList();
        this.diameterObjek = 0;
        this.DiameterLineImage = null;
        this.xKanan = 0;
        this.xKiri = 0;
        this.lebarCitra = 0;
        this.tinggiCitra = 0;
        this.maxGray = 0;
    }

    /**
     * Tentukan image yg akan di proses.
     *
     * @param input
     */
    public void setImage(BufferedImage input) {
        this.realImage = input;
        this.grayImage = null;
        this.negativeImage = null;
        this.logTransformationImage = null;
        this.setSize();
    }

    /**
     * Dapatkan image yg asli.
     *
     * @return
     */
    public BufferedImage getImage() {
        return this.realImage;
    }

    /**
     * Dapatkan image yg sudah diproses menjadi grayscale.
     *
     * @return
     */
    public BufferedImage getGrayImage() {
        return this.grayImage;
    }

    /**
     * Dapatkan image yg sudah diproses menjadi power-law transformation image.
     *
     * @return
     */
    public BufferedImage getPowerLawImage() {
        return this.powerLawImage;
    }

    /**
     * Dapatkan image yg sudah diproses menjadi binary image.
     *
     * @return
     */
    public BufferedImage getBinaryImage() {
        return this.binaryImage;
    }

    /**
     * Dapatkan image yg sudah di proses/max filter.
     *
     * @return
     */
    public BufferedImage getMaxImage() {
        return this.maxImage;
    }

    /**
     * Dapatkan image yg sudah di proses menjadi negatif.
     *
     * @return
     */
    public BufferedImage getNegativeImage() {
        return this.negativeImage;
    }

    /**
     * Dapatkan image yg sudah di proses menjadi log transformation.
     *
     * @return
     */
    public BufferedImage getLogTransformationImage() {
        return this.logTransformationImage;
    }

    /**
     * Mendapatkan diameter dari objek pada gambar.
     *
     * @return
     */
    public double getDiameter() {
        return this.diameterObjek;
    }

    public double getxKiriDiameter() {
        return this.xKiri;
    }

    public double getxKananDiameter() {
        return this.xKanan;
    }

    public BufferedImage getDiameterLine() {
        return this.DiameterLineImage;
    }

    /**
     * Dapatkan nilai max gray
     * @return 
     */
    public int getMaxGray(){
        return this.maxGray;
    }
    /**
     * Dapatkan mean red value dari image.
     *
     * @return
     */
    public double getR() {
        return this.meanR;
    }

    /**
     * Dapatkan mean green value dari image.
     *
     * @return
     */
    public double getG() {
        return this.meanG;
    }

    /**
     * Dapatkan mean blue value dari image.
     *
     * @return
     */
    public double getB() {
        return this.meanB;
    }

    /**
     * Mendapatkan dimensi lebar dari image.
     *
     * @return
     */
    public int getLebar() {
        return this.lebarCitra;
    }

    /**
     * Mendapatkan dimensi tinggi dari image.
     *
     * @return
     */
    public int getTinggi() {
        return this.tinggiCitra;
    }

    /**
     * Dapatkan ukuran image, dari image asli.
     */
    private void setSize() {
        this.tinggiCitra = realImage.getHeight();
        this.lebarCitra = realImage.getWidth();
    }

    /**
     * Pemrosesan image menjadi negatif image.
     */
    public void imagetoNegative() {
        int hasil;
        Color before, after;
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {
                before = new Color(grayImage.getRGB(x, y) & 0x00ffffff);
                hasil = maxGray - before.getRed();
                hasil = hasil < 0 ? 0 : hasil;
                hasil = hasil > 255 ? 255 : hasil;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(x, y, after.getRGB());
            }
        }
        this.negativeImage = output;
    }

    /**
     * Pemrosesan image menjadi power-law transformations image.
     *
     * @param c : constanta
     * @param gamma
     */
    public void imageToPowerLaw(double c, double gamma) {
        int hasil;
        Color before, after;
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {
                before = new Color(grayImage.getRGB(x, y) & 0x00ffffff);
                //System.out.println(Math.log((double)(1+before.getRed())));
                hasil = (int) (c * Math.pow(before.getRed(), gamma));
                hasil = hasil < 0 ? 0 : hasil;
                hasil = hasil > 255 ? 255 : hasil;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(x, y, after.getRGB());
            }
        }
        this.powerLawImage = output;
    }

    /**
     * Pemrosesan image menjadi log transformation image.
     *
     * @param c : constanta
     */
    public void imageToLogTransformation(double c) {
        int hasil;
        Color before, after;
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {
                before = new Color(grayImage.getRGB(x, y) & 0x00ffffff);
                //System.out.println(Math.log((double)(1+before.getRed())));
                hasil = (int) (c * Math.log((double) (1 + before.getRed())));
                hasil = hasil < 0 ? 0 : hasil;
                hasil = hasil > 255 ? 255 : hasil;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(x, y, after.getRGB());
            }
        }
        this.logTransformationImage = output;
    }

    /**
     * Pemrosesan image menjadi grayscale image.
     *
     * @param metode : metode grayscale
     */
    public void imageToGray(int metode) {
        // Init variable
        double red, green, blue;
        int gray = 0;
        Color before, after;

        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {

                before = new Color(realImage.getRGB(x, y) & 0x00ffffff);

                // Calculate RGB to gray
                // with lumonisity algorithm
                if (metode == 0) {
                    red = (double) (before.getRed());
                    green = (double) (before.getGreen());
                    blue = (double) (before.getBlue());
                    gray = (int) ((Math.max(green, Math.max(red, blue)) + Math.min(green, Math.min(red, blue))) / 2);
                } else if (metode == 1) {
                    red = (double) (before.getRed());
                    green = (double) (before.getGreen());
                    blue = (double) (before.getBlue());
                    gray = (int) ((red + green + blue) / 2);
                } else if (metode == 2) {
                    red = (double) (before.getRed() * 0.2989);
                    green = (double) (before.getGreen() * 0.5870);
                    blue = (double) (before.getBlue() * 0.1140);
                    gray = (int) (red + green + blue);
                }
                maxGray = gray > maxGray ? gray : maxGray;
                gray = gray < 0 ? 0 : gray;
                gray = gray > 255 ? 255 : gray;
                after = new Color(gray, gray, gray);

                output.setRGB(x, y, after.getRGB());

            }
        }
        this.grayImage = output;
    }

    public BufferedImage imageToBitPlane(int level) {
        int hasil;
        String matrixBit[][][] = new String[lebarCitra][tinggiCitra][8];
        Color before, after;
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {
                before = new Color(grayImage.getRGB(x, y) & 0x00ffffff);
                matrixBit[x][y] = decimalToBinary(before.getRed()).split(" ");
                hasil = Integer.parseInt(matrixBit[x][y][level]);
                hasil = hasil == 1 ? 255 : hasil;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(x, y, after.getRGB());
            }
        }
        return output;
    }

    private String decimalToBinary(int num) {
        int[] binArr = new int[8];
        int j = 0;
        while (num != 0) {
            int digit = num % 2;
            binArr[binArr.length - 1 - j] = digit;
            num = num / 2;
            j++;
        }
        return (Arrays.toString(binArr).replaceAll("\\,|\\[|\\]", ""));
    }

    /**
     * Pemrosesan image menjadi binary image.
     */
    public void imageToBinary() {
        Color before, after;
        koordObjek.clear();

        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {

                before = new Color(this.grayImage.getRGB(x, y) & 0x00ffffff);

                if (before.getBlue() > 150) {
                    after = new Color(255, 255, 255);
                    koordObjek.add(String.valueOf(x) + "," + String.valueOf(y));
                } else {
                    after = new Color(0, 0, 0);
                }
                output.setRGB(x, y, after.getRGB());
            }
        }
        this.binaryImage = output;
    }

    public BufferedImage imageSubstraction(BufferedImage gambar1) {
        Color before, after, before2;
        int hasil;
        koordObjek.clear();
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {

                before = new Color(this.grayImage.getRGB(x, y) & 0x00ffffff);
                before2 = new Color(gambar1.getRGB(x, y) & 0x00ffffff);
                hasil = (int) ((before.getRed() - before2.getRed()));
                hasil = hasil < 0 ? (int) (Math.sqrt(Math.pow(hasil, 2))) : hasil;
                hasil = hasil > 255 ? 255 : hasil;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(x, y, after.getRGB());
            }
        }
        return output;
    }

    public BufferedImage operasiLogika(BufferedImage gambar2, int jenis) {
        int hasil;
        String result;
        String matrixBit1[][][] = new String[lebarCitra][tinggiCitra][8];
        String matrixBit2[][][] = new String[lebarCitra][tinggiCitra][8];
        Color before1, before2, after;
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {
                before1 = new Color(grayImage.getRGB(x, y) & 0x00ffffff);
                before2 = new Color(gambar2.getRGB(x, y) & 0x00ffffff);
                matrixBit1[x][y] = decimalToBinary(before1.getRed()).split(" ");
                matrixBit2[x][y] = decimalToBinary(before2.getRed()).split(" ");
                result = "";
                for (int i = 0; i < 8; i++) {
                    if (0 == jenis) {
                        if ("1".equals(matrixBit1[x][y][i]) && "1".equals(matrixBit2[x][y][i])) {
                            result += 1;
                        } else {
                            result += 0;
                        }
                    } else if (1 == jenis) {
                        if ("1".equals(matrixBit1[x][y][i]) || "1".equals(matrixBit2[x][y][i])) {
                            result += 1;
                        } else {
                            result += 0;
                        }
                    } else {
                        result += matrixBit1[x][y][i].equals(matrixBit2[x][y][i]) ? "0" : "1";
                    }
                }
                hasil = Integer.parseInt(result, 2);
                hasil = hasil < 0 ? 0 : hasil;
                hasil = hasil > 255 ? 255 : hasil;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(x, y, after.getRGB());
            }
        }
        return output;
    }

    /**
     * Proses image binary menggunakan algoritma max filter.
     */
    private void imageToMax() {
        //Size window matrix, untuk matrik konvolusi
        int size = 3; // nilainya harus >=3 dan harus ganjil

        //tambahkan 1px diluar image, untuk pengecekan
        BufferedImage largerImage = addBoundary(this.binaryImage);

        //Ukuran matriks window size ^ 2
        int[] matriksWindow = new int[(int) Math.pow(size, 2)];
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);

        //Lakukan pengecekan nilai max per matrix window terhadap image
        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {
                matriksWindow = getWindow(largerImage, x + 1, y + 1, size);
                output.setRGB(x, y, getMax(matriksWindow));
            }
        }
        this.maxImage = output;
    }

    /**
     * Dapatkan max value dari array of integer.
     *
     * @param arrayInt
     * @return
     */
    private static int getMax(int[] arrayInt) {
        int max = arrayInt[0];
        for (int i = 1; i < arrayInt.length; i++) {
            if (arrayInt[i] > max) {
                max = arrayInt[i];
            }
        }
        return max;
    }

    /**
     * Dapatkan array of integer dari windows image koordinat x dan y.
     *
     * @param img
     * @param x
     * @param y
     * @return
     */
    private static int[] getWindow(BufferedImage img, int x, int y, int sizeWindow) {
        int[] output = new int[(int) Math.pow(sizeWindow, 2)];
        int plus = 0;
        for (int i = 0; i < sizeWindow; i++) {
            for (int j = 0; j < sizeWindow; j++) {
                output[plus] = img.getRGB((x - 1) + i, (y - 1) + j);
                plus++;
            }
        }
        return output;
    }

    /**
     * Tambahkan border di luar image.
     *
     * @param input
     * @return
     */
    private BufferedImage addBoundary(BufferedImage input) {
        //Buat buffered image ukuran lebar dan tinggi + 2
        BufferedImage output = new BufferedImage(lebarCitra + 2, tinggiCitra + 2,
                BufferedImage.TYPE_BYTE_GRAY);
        //Buat gambar
        Graphics gr = output.getGraphics();

        //Buat mewarnai
        gr.setColor(Color.black);
        gr.fillRect(0, 0, output.getWidth(), output.getHeight());

        //Taruh buffered image input ke yg baru dimulai koordinat 1,1
        gr.drawImage(input, 1, 1, null);

        return output;
    }

    /**
     * Mendapatkan mean RGB dari objek gambar.
     *
     * @param input
     * @return color
     */
    private void hitungMeanR_G_B() {
        int length = koordObjek.size();
        double red = 0;
        double green = 0;
        double blue = 0;
        int x, y;
        Color before;
        String xy[];
        for (int i = 0; i < length; i++) {
            xy = koordObjek.get(i).toString().split(",");

            //System.out.println(xy[0] + "-" + xy[1]);
            x = Integer.parseInt(xy[0]);
            y = Integer.parseInt(xy[1]);
            before = new Color(this.realImage.getRGB(x, y) & 0x00ffffff);
            red += before.getRed();
            blue += before.getBlue();
            green += before.getGreen();
        }
        red /= length;
        blue /= length;
        green /= length;

        this.meanR = red;
        this.meanG = green;
        this.meanB = blue;

        System.out.println("meanR :" + red);
    }

    /**
     * Mencari diameter dari citra, input haruslah berupa citra biner hitam
     * putih
     *
     */
    private void hitungDiameter() {
        int xKanan_hitung;
        int xKiri_hitung;

        //dari tepi kiri
        outloop:
        for (xKiri_hitung = 0; xKiri_hitung < lebarCitra; xKiri_hitung++) {
            for (int yKiri_hitung = 0; yKiri_hitung < tinggiCitra; yKiri_hitung++) {
                Color c = new Color(binaryImage.getRGB(xKiri_hitung, yKiri_hitung) & 0x00ffffff);
                if (c.getGreen() == 255) {
                    break outloop;
                }
            }
        }

        //dari tepi kanan
        outloop2:
        for (xKanan_hitung = lebarCitra - 1; xKanan_hitung > 0; xKanan_hitung--) {
            for (int yKanan_hitung = 0; yKanan_hitung < tinggiCitra; yKanan_hitung++) {
                Color d = new Color(binaryImage.getRGB(xKanan_hitung, yKanan_hitung) & 0x00ffffff);
                if (d.getGreen() == 255) {
                    break outloop2;
                }
            }
        }
        //Kembalikan nilai selisih x
        this.diameterObjek = (xKanan_hitung - xKiri_hitung);
        this.xKanan = xKanan_hitung;
        this.xKiri = xKiri_hitung;

    }

    //membuat garis diameter
    private void addDiameterLine() {

        //Buat buffered image ukuran lebar dan tinggi yang sama
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_3BYTE_BGR);

        //Buat gambar
        Graphics g = output.getGraphics();
        Graphics2D g2d = (Graphics2D) g.create();

        //Buat mewarnai
        //g.setColor(Color.BLUE);
        //g.fillRect(0,0,output.getWidth(),output.getHeight());
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, output.getWidth(), output.getHeight());

        //Taruh buffered image input ke yg baru dimulai koordinat 0,0
        //g.drawImage(this.realImage, 0, 0, null);
        g2d.drawImage(this.realImage, 0, 0, null);

        // membuat garis batas kiri
        int xLoc = (int) xKiri;
        int yLoc = 0;
        int xxLoc = (int) xKiri;
        int yyLoc = tinggiCitra;
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(xLoc, yLoc, xxLoc, yyLoc);

        //g.drawLine(xLoc, yLoc, xxLoc, yyLoc);
        // membuat garis batas kanan
        int xLoc_ = (int) xKanan;
        int yLoc_ = 0;
        int xxLoc_ = (int) xKanan;
        int yyLoc_ = tinggiCitra;

        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(xLoc_, yLoc_, xxLoc_, yyLoc_);

        //g.drawLine(xLoc_, yLoc_, xxLoc_, yyLoc_);
        this.DiameterLineImage = output;
    }
}
