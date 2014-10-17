package imageHelper;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ImageProcessing {

    private ArrayList koordObjek;
    private BufferedImage realImage;
    private BufferedImage grayImage;
    private BufferedImage binaryImage;
    private BufferedImage negativeImage;
    private BufferedImage logTransformationImage;
    private BufferedImage powerLawImage;
    private BufferedImage equalImage;
    private BufferedImage smoothingImage;
    private BufferedImage sharpeningImage;
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
        this.equalImage = null;
        this.smoothingImage = null;
        this.sharpeningImage = null;
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
        this.equalImage = null;
        this.smoothingImage = null;
        this.sharpeningImage = null;
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
     * Dapatkan image yg sudah di proses menjadi equalisasi.
     *
     * @return
     */
    public BufferedImage getEqualImage() {
        return this.equalImage;
    }

    /**
     * Mendapatkan image yang sudah diproses smoothing
     *
     * @return
     */
    public BufferedImage getSmoothingImage() {
        return this.smoothingImage;
    }

    /**
     * Mendapatkan image yang sudah diproses sharpening
     *
     * @return
     */
    public BufferedImage getSharpeningImage() {
        return this.sharpeningImage;
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
     * Mendapatkan data histogram
     *
     * @return
     */
    public ArrayList<ItemHistogram> getHistogram() {
        return this.histogram;
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

    /**
     * Fungsi untuk menghitung hitogram image dan ekualisasi
     */
    public void setHistogram() {
        ArrayList<ItemHistogram> ad = new ArrayList<>();
        ArrayList<ItemHistogram> sort = new ArrayList<>();
        Color before, after;
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 1; y < tinggiCitra; y++) {
            for (int x = 1; x < lebarCitra; x++) {
                before = new Color(grayImage.getRGB(x, y) & 0x00ffffff);
                int nilaiRGB = before.getRed();
                boolean ketemu = false;
                for (ItemHistogram ad1 : ad) {
                    if (nilaiRGB == ad1.getRgb()) {
                        ad1.setJumlah(ad1.getJumlah() + 1);
                        ketemu = true;
                    }
                }
                if (!ketemu) {
                    ItemHistogram itemBaru = new ItemHistogram(nilaiRGB, 1);
                    ad.add(itemBaru);
                }
            }
        }

        ad.get(0).setJumlahKumulatif(ad.get(0).getJumlah());
        sort = ad;
        Collections.sort(ad, new Comparator<ItemHistogram>() {
            @Override
            public int compare(ItemHistogram ih1, ItemHistogram ih2) {
                if (ih1.getRgb() > ih2.getRgb()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        sort.get(0).setJumlahKumulatif(sort.get(0).getJumlah());
        for (int i = 1; i < sort.size(); i++) {
            sort.get(i).setJumlahKumulatif(sort.get(i - 1).getJumlahKumulatif() + sort.get(i).getJumlah());
            double hasilBagi = (double) sort.get(i).getJumlahKumulatif() / (this.lebarCitra * this.tinggiCitra);
            sort.get(i).setHasilEkualisasi((int) (hasilBagi * 255));
        }
        histogram = sort;

        for (int i = 0; i < this.lebarCitra; i++) {
            for (int j = 0; j < this.tinggiCitra; j++) {
                for (ItemHistogram sort1 : sort) {
                    before = new Color(grayImage.getRGB(i, j) & 0x00ffffff);
                    int nilaiRGB = before.getRed();
                    if (nilaiRGB == sort1.getRgb()) {
                        int hasil = sort1.getHasilEkualisasi();
                        after = new Color(hasil, hasil, hasil);
                        output.setRGB(i, j, after.getRGB());
                    }
                }
            }
        }
        this.equalImage = output;
    }

    /**
     * Fungsi untuk mengubah image dengan teknik smoothing
     */
    public void setSmoothingImage() {
        Color before, after;
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        int boxFilter[][] = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int weightedAverage[][] = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
        int rgbPlus[][] = new int[(lebarCitra + 2)][(tinggiCitra + 2)];
        for (int i = 0; i < this.lebarCitra; i++) {
            for (int j = 0; j < this.tinggiCitra; j++) {
                before = new Color(grayImage.getRGB(i, j) & 0x00ffffff);
                rgbPlus[i + 1][j + 1] = before.getRed();
            }
        }
        for (int i = 1; i < rgbPlus.length-1; i++) {
            for (int j = 1; j < rgbPlus[0].length-1; j++) {
                
                int tempI = i - 1;
                int tempJ = j - 1;
                 int jumlah = 0;
                jumlah += rgbPlus[i-1][j-1]*boxFilter[0][0];
                jumlah += rgbPlus[i-1][j]*boxFilter[0][1];
                jumlah += rgbPlus[i-1][j+1]*boxFilter[0][2];
                jumlah += rgbPlus[i][j-1]*boxFilter[1][0];
                jumlah += rgbPlus[i][j]*boxFilter[1][1];
                jumlah += rgbPlus[i][j+1]*boxFilter[1][2];
                jumlah += rgbPlus[i+1][j-1]*boxFilter[2][0];
                jumlah += rgbPlus[i+1][j]*boxFilter[2][1];
                jumlah += rgbPlus[i+1][j+1]*boxFilter[2][2];
                int hasil = jumlah/9;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(i-1, j-1, after.getRGB());
            }
        }
        this.smoothingImage = output;
    }

    /**
     * Fungsi untuk mengubah image dengan teknik sharpening
     */
    public void setSharpeningImage() {
        Color before, after;
        BufferedImage output = new BufferedImage(lebarCitra, tinggiCitra,
                BufferedImage.TYPE_BYTE_GRAY);
        
        int sobel[][][] = {{{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}},{{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}}};
        int weightedAverage[][] = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
        int rgbPlus[][] = new int[(lebarCitra + 2)][(tinggiCitra + 2)];
        for (int i = 0; i < this.lebarCitra; i++) {
            for (int j = 0; j < this.tinggiCitra; j++) {
                before = new Color(grayImage.getRGB(i, j) & 0x00ffffff);
                rgbPlus[i + 1][j + 1] = before.getRed();
            }
        }
        for (int i = 1; i < rgbPlus.length-1; i++) {
            for (int j = 1; j < rgbPlus[0].length-1; j++) {
               int jumlah[] = new int[2];
                jumlah[0] = 0;
                jumlah[1] = 0;
                
                jumlah[0] += rgbPlus[i-1][j-1]*sobel[0][0][0];
                jumlah[0] += rgbPlus[i-1][j]*sobel[0][0][1];
                jumlah[0] += rgbPlus[i-1][j+1]*sobel[0][0][2];
                jumlah[0] += rgbPlus[i][j-1]*sobel[0][1][0];
                jumlah[0] += rgbPlus[i][j]*sobel[0][1][1];
                jumlah[0] += rgbPlus[i][j+1]*sobel[0][1][2];
                jumlah[0] += rgbPlus[i+1][j-1]*sobel[0][2][0];
                jumlah[0] += rgbPlus[i+1][j]*sobel[0][2][1];
                jumlah[0] += rgbPlus[i+1][j+1]*sobel[0][2][2];
                
                jumlah[1] += rgbPlus[i-1][j-1]*sobel[1][0][0];
                jumlah[1] += rgbPlus[i-1][j]*sobel[1][0][1];
                jumlah[1] += rgbPlus[i-1][j+1]*sobel[1][0][2];
                jumlah[1] += rgbPlus[i][j-1]*sobel[1][1][0];
                jumlah[1] += rgbPlus[i][j]*sobel[1][1][1];
                jumlah[1] += rgbPlus[i][j+1]*sobel[1][1][2];
                jumlah[1] += rgbPlus[i+1][j-1]*sobel[1][2][0];
                jumlah[1] += rgbPlus[i+1][j]*sobel[1][2][1];
                jumlah[1] += rgbPlus[i+1][j+1]*sobel[1][2][2];
                
                jumlah[0] = (int)Math.sqrt(Math.pow(jumlah[0], 2));
                jumlah[1] = (int)Math.sqrt(Math.pow(jumlah[1], 2));
                int hasil = jumlah[0] + jumlah[1];
                hasil = hasil < 0 ? 0 : hasil;
                hasil = hasil > 255 ? 255 : hasil;
                after = new Color(hasil, hasil, hasil);
                output.setRGB(i-1, j-1, after.getRGB());
            }
        }
        this.sharpeningImage = output;
    }
}
