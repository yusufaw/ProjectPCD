package imageHelper;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageProcessing {
    private ArrayList koordObjek;
    private BufferedImage realImage;
    private BufferedImage grayImage;
    private BufferedImage binaryImage;
    private BufferedImage negativeImage;
    private BufferedImage logTransformationImage;
    private BufferedImage powerLawImage;
    private int tinggiCitra;
    private int lebarCitra;
    private int maxGray;
    private ArrayList histogram;

    /**
     * Konstruktor
     */
    public ImageProcessing() {
        this.realImage = null;
        this.grayImage = null;
        this.binaryImage = null;
        this.negativeImage = null;
        this.logTransformationImage = null;
        this.powerLawImage = null;
        this.koordObjek = new ArrayList();
        this.lebarCitra = 0;
        this.tinggiCitra = 0;
        this.maxGray = 0;
        histogram = new ArrayList();
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
     * Dapatkan nilai max gray
     *
     * @return
     */
    public int getMaxGray() {
        return this.maxGray;
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
    public void imageToNegative() {
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

    /**
     * Pemrosesan image menjadi Bit Plane image.
     *
     * @param level : level bit (0,1,2,3,4,5,6,7)
     * @return BufferedImage : hasil gambar Bit Plane
     */
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

    /**
     * Mengubah angka desimal dalam bentuk biner.
     *
     * @param num : angka desimal
     */
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

    /**
     * Pemrosesan image substraction dari 2 gambar
     *
     * @param gambar2 : gambar ke 2 yang ingin dibandingkan
     * @return BufferedImage : gambar hasil dari image substraction
     */
    public BufferedImage imageSubstraction(BufferedImage gambar2) {
        Color before, after, before2;
        int hasil;
        koordObjek.clear();
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < tinggiCitra; y++) {
            for (int x = 0; x < lebarCitra; x++) {

                before = new Color(this.grayImage.getRGB(x, y) & 0x00ffffff);
                before2 = new Color(gambar2.getRGB(x, y) & 0x00ffffff);
                hasil = (int) ((before.getRed() - before2.getRed()));
                hasil = hasil < 0 ? (int) (Math.sqrt(Math.pow(hasil, 2))) : hasil;
                hasil = hasil > 255 ? 255 : hasil;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(x, y, after.getRGB());
            }
        }
        return output;
    }

    /**
     * Pemrosesan image dengan operasi logika dari 2 citra.
     *
     * @param gambar2 : citra 2 atau mask yang akan diproses dengan gambar saat
     * ini.
     * @param jenis : jenis operasi logika (0:AND, 1:OR, 2:XOR)
     * @return BufferedImage : gambar hasil dari operasi logika dari 2 citra
     */
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
    
    public void setHistogram(){
    }
}
