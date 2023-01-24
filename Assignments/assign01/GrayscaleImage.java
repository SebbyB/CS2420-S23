package assign01;


import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel" brightnesses
 * 255 is "white" 127 is "gray" 0 is "black" with intermediate values in between
 * Author: Ben Jones and ***STUDENT FILL YOUR NAME IN***
 */
public class GrayscaleImage {
    private double[][] imageData; // the actual image data
    private int numRows;
    private int numColumns;


    /**
     * Initialize an image from a 2D array of doubles
     * This constructor creates a copy of the input array
     * @param data initial pixel values
     * @throws IllegalArgumentException if the input array is empty or "jagged" meaning not all rows are the same length
     */
    public GrayscaleImage(double[][] data){
        if(data.length == 0 || data[0].length == 0){
            throw new IllegalArgumentException("Image is empty");
        }

        imageData = new double[data.length][data[0].length];
        for(var row = 0; row < imageData.length; row++){
            if(data[row].length != imageData[row].length){
                throw new IllegalArgumentException("All rows must have the same length");
            }
            for(var col = 0; col < imageData[row].length; col++){
                imageData[row][col] = data[row][col];
            }
        }
        numRows = data.length;
        numColumns = data[0].length;
    }

    /**
     * Fetches an image from the specified URL and converts it to grayscale
     * Uses the AWT Graphics2D class to do the conversion, so it may add
     * an item to your dock/menu bar as if you're loading a GUI program
     * @param url where to download the image
     * @throws IOException if the image can't be downloaded for some reason
     *
     */
    public GrayscaleImage(URL url) throws IOException {
        var inputImage = ImageIO.read(url);
        //convert input image to grayscale
        //based on (https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models)
        var grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d= grayImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();
        imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

        //raster is basically a width x height x 1 3-dimensional array
        var grayRaster = grayImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                //getSample parameters are x (our column) and y (our row), so they're "backwards"
                imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
            }
        }
        numRows = imageData.length;
        numColumns = imageData[0].length;
    }

    public void savePNG(File filename) throws IOException {
        var outputImage = new BufferedImage(imageData[0].length, imageData.length, BufferedImage.TYPE_BYTE_GRAY);
        var raster = outputImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                raster.setSample(col, row, 0, imageData[row][col]);
            }
        }
        ImageIO.write(outputImage, "png", filename);
    }

    ///Methods to be filled in by students below

    /**
     * Getter Method for the number of rows in the color data matrix.
     * @return - number of rows as an int.
     */
    public int getNumRows(){
        return this.numRows;
    }
    /**
     * Getter Method for the number of columns in the color data matrix.
     * @return - number of columns as an int.
     */
    public int getNumColumns(){
        return this.numColumns;
    }

    /**
     * Get the pixel brightness value at the specified coordinates
     * (0,0) is the top left corner of the image, (width -1, height -1) is the bottom right corner
     * @param x horizontal position, increases left to right
     * @param y vertical position, **increases top to bottom**
     * @return the brightness value at the specified coordinates
     * @throws IllegalArgumentException if x, y are not within the image width/height
     */
    public double getPixel(int x, int y){

        if(x > this.numColumns || y > this.numRows){
            throw new IllegalArgumentException();
        }


        return this.imageData[y][x];
    }

    /**
     * Two images are equal if they have the same size and each corresponding pixel
     * in the two images is exactly equal
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof GrayscaleImage)){
            return false;
        }

        GrayscaleImage otherImage = (GrayscaleImage)other;

        double[][] thisImageData = this.imageData;
        double[][] otherImageData = otherImage.imageData;

        int otherImageN = otherImage.getNumRows();
        int otherImageM = otherImage.getNumColumns();
        int thisImageN = this.getNumRows();
        int thisImageM = this.getNumColumns();
        if (otherImageN == thisImageN && thisImageM == otherImageM){
            for (int i = 0; i < otherImageN; i++){
                for(int j = 0; j < otherImageM; j++){
                    if(thisImageData[i][j] != otherImageData[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }


    /**
     * Computes the average of all values in image data
     * @return the average of the imageData array
     */
    public double averageBrightness(){
        double average,averageVal = 0, averageNum = 0;

        for(int i = 0; i < this.numRows; i++){
            for (int j = 0; j < this.numColumns; j++){
                averageVal += this.imageData[i][j];
                averageNum++;
            }
        }
        average = averageVal/averageNum;
        return average;
    }

    /**
     * Return a new GrayScale image where the average new average brightness is 127
     * To do this, uniformly scale each pixel (ie, multiply each imageData entry by the same value)
     * Due to rounding, the new average brightness will not be 127 exactly, but should be very close
     * The original image should not be modified
     * @return a GrayScale image with pixel data uniformly rescaled so that its averageBrightness() is 127
     */
    public GrayscaleImage normalized(){
        double avgBrightness = this.averageBrightness(), scalarValue = 127/avgBrightness;
        double[][] imgData = this.imageData, normData = new double[this.numRows][this.numColumns];
        for (int i =0; i < this.numRows; i++){
            for (int j = 0; j < this.numColumns; j++){
                normData[i][j] = imgData[i][j] * scalarValue;
            }
        }
        GrayscaleImage normalizedImage = new GrayscaleImage(normData);

        return normalizedImage;
    }


    /**
     * Returns a new grayscale image that has been "mirrored" across the y-axis
     * In other words, each row of the image should be reversed
     * The original image should be unchanged
     * @return a new GrayscaleImage that is a mirrored version of the this
     */
    public GrayscaleImage mirrored(){

        double[][] imgData = this.imageData, mirroredData = new double[this.numRows][this.numColumns];
        for (int i = 0; i < this.numRows; i++){
            int j = numColumns - 1, k = 0;
            while (j >= 0 && k < numColumns){
                mirroredData[i][j] = imgData[i][k];
                k++;
                j--;
            }
        }

        return new GrayscaleImage(mirroredData);
    }

    /**
     * Returns a new GrayscaleImage of size width x height, containing the part of `this`
     * from startRow -> startRow + height, startCol -> startCol + width
     * The original image should be unmodified
     * @param startRow
     * @param startCol
     * @param width
     * @param height
     * @return A new GrayscaleImage containing the sub-image in the specified rectangle
     * @throws IllegalArgumentException if the specified rectangle goes outside the bounds of the original image
     */
    public GrayscaleImage cropped(int startRow, int startCol, int width, int height){

        //Checks if the image needs to be cropped at all based off of the parameters. If not then just return this.
        if (
                ((startRow == 0) && (startCol == 0))

                && ((width == this.numColumns) && (height == this.numRows))
        ){
            return this;
        }
        // Checks if the start points are within the set of pixels. If not then throw an IllegalArgumentException.
        if(
                ((startRow < 0) || (startCol < 0))
                || ((startRow > this.numRows) || (startCol > this.numColumns))
        ){
            throw new IllegalArgumentException();
        }
        //Checks if the rectangle is in the image. If not then throw an Illegal Argument Exception.
        int endColumn = startCol + width, endRow = startRow + height;
        if(
                (endRow > this.numRows || endColumn > this.numColumns)
                || (endRow < 0 || endColumn < 0)

        ){
            throw new IllegalArgumentException();
        }

        //Crops the image.
        double[][] imgData = this.imageData, croppedData = new double[height][width];
        int i = 0, n = startRow;
        while(i < height && n < endRow){
           int j=0,m = startCol;
            while (j < width && m < endColumn){
                croppedData[i][j] = imgData[n][m];
                j++;
                m++;
            }
            i++;
            n++;
        }
        return new GrayscaleImage(croppedData);
    }

    /**
     * Returns a new "centered" square image (new width == new height)
     * For example, if the width is 20 pixels greater than the height,
     * this should return a height x height image, with 10 pixels removed from the left and right
     * edges of the image
     * If the number of pixels to be removed is odd, remove 1 fewer pixel from the left or top part
     * (note this convention should be SIMPLER/EASIER to implement than the alternative)
     * The original image should not be changed
     * @return a new, square, GrayscaleImage
     */
    public GrayscaleImage squarified(){
        int delta = Math.abs(this.numColumns - this.numRows);

        //If the image is wider than it is tall:
        if(this.numColumns > this.numRows){
            //the difference in width.
//            delta = this.numColumns - this.numRows;
            int left,right;
            //If the difference is odd, the image should be cropped more on the right than the left.
            left = (delta / 2);
            right = delta/2 + (delta % 2);
            GrayscaleImage image = new GrayscaleImage(this.imageData).cropped(0,left,this.numColumns - delta,this.numRows);
            return image;
        }
        //If the image is taller than it is wide:
        else if(this.numColumns < this.numRows){
            //The difference in height.
//            delta = this.numColumns - this.numRows;
            int top,bottom;
            //if the difference is odd,
            bottom = (delta / 2)+ (delta % 2);
            top = delta/2 ;
            GrayscaleImage image = new GrayscaleImage(this.imageData).cropped(top,0,this.numColumns,this.numRows - delta);
            return image;
        }
        //If the image is already square.
        else {
            return this;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        double[][] imgData = this.imageData;
        for (int i =0; i < this.numRows; i++){
            builder.append("|");
            for (int j = 0; j < this.numColumns; j++){
                builder.append(Double.toString(imgData[i][j]) + " ");
            }
            builder.append("|\n");
        }
        return builder.toString();
    }
}
