package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage nSizeTestImg;
    private GrayscaleImage croppedImage;
    private int n = 500;

    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});

        double[][] testBack = new double[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                testBack[i][j] = j+1;
            }
        }
        nSizeTestImg = new GrayscaleImage(testBack);
    }

    /**
     * Tests GrayScaleImage's getPixel() method.
     */
    @Test
    void testGetPixel(){

        /**
         * On an arbitrary image, grabs a pixel and compares its value to the value we should expect.
         */
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                double pixel = nSizeTestImg.getPixel(j,i);
                double expVal = j+1;
                assertEquals(expVal,pixel);
            }
        }

        assertThrows(IllegalArgumentException.class,() -> {nSizeTestImg.getPixel(n+1,n+1);});

    }

    /**
     * Tests GrayScaleImage's equals() Method.
     */
    @Test
    void testEquals() {
        //Pre-made Test
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(smallSquare, equivalent);


        GrayscaleImage N,M;
        double[][] d = new double[n][n];
        double[][] e = new double[n][n];

        /**
         * Uses two GrayScaleImages of the same size.
         * Populates their values so that they should be equal and then checks to see that they are and the method returns correctly.
         * Then Populates their values so that they should not be equal and checks that the method returns correctly.
         */
        for(double number = 0; number < n; number++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = number;
                    e[i][j] = number;
                }
            }
            N = new GrayscaleImage(d);
            M = new GrayscaleImage(e);
            assertEquals(N,M);
        }
        for(double number = 0; number < n; number++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = number++;
                    e[i][j] = number;
                }
            }
            N = new GrayscaleImage(d);
            M = new GrayscaleImage(e);
            assertNotEquals(N,M);
        }

        /**
         * Checks to make sure the method returns false if they are different sized images and if they are different object types.
         */
        double[][] l = new double[n+1][n+1], p = new double[n][n];
       GrayscaleImage L = new GrayscaleImage(l), P = new GrayscaleImage(p);
       assertNotEquals(L,P);
       assertNotEquals(L,l);


    }

    /**
     * Tests GrayScaleImage's averageBrightness() method.
     */
    @Test
    void averageBrightness() {
        //Premade Test.
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);


        /**
         * Test that works with an arbitrarily sized Image.
         * It first creates a nxn 2-D array, where all the values are the same number. The average of this should be equal to the number.
         * It then makes that 2-D array into an image and compares the value returned by averageBrightness() and the value we should expect.
         */
        GrayscaleImage N;
        double[][] d = new double[n][n];
        for(double number = 0; number < n; number++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = number;
                }
            }
            N = new GrayscaleImage(d);
            assertEquals(number,N.averageBrightness());
        }
    }

    /**
     * Tests GrayScaleImage's normalized() method.
     */
    @Test
    void normalized() {
        //Pre-made Test
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }


    /**
     * Creates an image of an arbitrary size and with an arbitrarily picked average brightness.
     * Normalizes it.
     * Checks to make sure postNormalized image always has an average brightness of 127.
     */
        GrayscaleImage N,M;
        double[][] d = new double[n][n];
        for(double number = 1; number <= n; number++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = number;
                }
            }
            N = new GrayscaleImage(d);
            assertEquals(number,N.averageBrightness());
            M = N.normalized();
            assertEquals(127,M.averageBrightness(),.001);
        }

    }


    /**
     * Tests if the GrayscaleImage.mirrored() method works as intended.
     */
    @Test
    void mirrored() {


        //PreMade Test
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});

        assertEquals(smallSquare.mirrored(), expected,smallSquare.mirrored().toString());

        /**
         * My Test
         * Note: This test uses ArrayLists, which we haven't covered in class. They are, however, not used in the actual GrayScaleImage methods.
         * I just wanted to use the Collections.reverse() method to reverse the array, rather than manually reversing the array.
         *
         * This generalizes to any NxN or NxM sized image... ie. adjusting the parameters doesn't make a difference.
         * It takes our arbitrary test image and generates a mirror. It then creates two lists, list1 and list2, for every row in the image.
         * list1 will contain the row data for the original image, while list2 will contain the row data for the mirrored image.
         * After the lists are populated, it then reverses list1 and compares it to list2. They should be equal by definition.
         */
        ArrayList<Double> list1, list2;
        GrayscaleImage mirroredTest = nSizeTestImg.mirrored();
        for (int i = 0; i < nSizeTestImg.getNumRows(); i++){
            list1 = new ArrayList<>();
            list2 = new ArrayList<>();
            for (int j = 0; j < nSizeTestImg.getNumColumns(); j++){
                list1.add(nSizeTestImg.getPixel(j,i));
                list2.add(mirroredTest.getPixel(j,i));
            }
            Collections.reverse(list1);
            assertEquals(list1,list2);
        }
    }





    @Test
    void cropped() {
        //Pre-madeTest
        var cropped = smallSquare.cropped(1,1,1,1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));

        //Crops the nSizeTestImg at every possible combination including where it isn't actually cropped.
        for(int t = 0 ; t < n; t++) {
            double[][] croppedBack = new double[n-t][n-t];
            int a = 0, b = t;
            while(b < n){
                int c = 0 ,d = t;
                while(d < n){
                    croppedBack[a][c] = nSizeTestImg.getPixel(d,b);
                    c++;
                    d++;
                }
                a++;
                b++;

            }
            //If they're correctly cropped they should be equal. They should also have the same number of rows and columns.
            //The number of rows and columns should be exactly
            GrayscaleImage ogCropped = nSizeTestImg.cropped(t,t,n-t,n-t), expectedCropped = new GrayscaleImage(croppedBack);
            assertEquals(ogCropped,expectedCropped);
            assertEquals(ogCropped.getNumColumns(),expectedCropped.getNumColumns());
            assertEquals(ogCropped.getNumRows(),expectedCropped.getNumRows());
            assertEquals(ogCropped.getNumColumns(), n-t);
            assertEquals(ogCropped.getNumRows(), n-t);
            assertEquals(expectedCropped.getNumColumns(), n-t);
            assertEquals(expectedCropped.getNumRows(), n-t);
        }


        //Tests the cropped image is just the image it just returns the image.
        GrayscaleImage sloppyCroppy = nSizeTestImg.cropped(0,0,n,n);
        assertEquals(sloppyCroppy,nSizeTestImg);

        //Tests to make sure if the arguments are outside the image it throws an exception.
        assertThrows(IllegalArgumentException.class,() -> {nSizeTestImg.cropped(0,0,n+1,n);});
        assertThrows(IllegalArgumentException.class,() -> {nSizeTestImg.cropped(0,0,n,n+1);});
        assertThrows(IllegalArgumentException.class,() -> {nSizeTestImg.cropped(1,0,n,n);});
        assertThrows(IllegalArgumentException.class,() -> {nSizeTestImg.cropped(0,1,n,n);});
}

    @Test
    void squarified() {
        //PreMadeTest
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1, 2}, {4, 5}});
        assertEquals(squared, expected);


        //An image that is already square should just return itself.
        assertEquals(nSizeTestImg, nSizeTestImg.squarified());


        //Any Image that is wider than it is tall.
        for (int t = 1; t < n; t++) {
            double[][] expectedSquare = new double[n][n], widerRectangle = new double[n][n+t];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n + t; j++) {
                    if(j < n){
                        expectedSquare[i][j] = t;
                    }
                    widerRectangle[i][j] = t;
                }
            }
            GrayscaleImage widerImage = new GrayscaleImage(widerRectangle), squaredImage = widerImage.squarified(),expectedImage = new GrayscaleImage(expectedSquare);
            assertEquals(expectedImage,squaredImage);
            assertNotEquals(expectedImage,widerImage);
            assertEquals(expectedImage.getNumColumns(),n);
            assertEquals(expectedImage.getNumRows(),n);
            assertEquals(squaredImage.getNumColumns(),n);
            assertEquals(squaredImage.getNumRows(),n);
            assertEquals(widerImage.getNumColumns(),n+t);
            assertEquals(widerImage.getNumRows(),n);
        }

        //Any Image that is taller than it is wide.
        for (int t = 1; t < n; t++) {
            double[][] expectedSquare = new double[n][n], widerRectangle = new double[n+t][n];
            for (int i = 0; i < n + t; i++) {
                for (int j = 0; j < n; j++) {
                    if(i < n){
                        expectedSquare[i][j] = t;
                    }
                    widerRectangle[i][j] = t;
                }
            }
            GrayscaleImage widerImage = new GrayscaleImage(widerRectangle), squaredImage = widerImage.squarified(),expectedImage = new GrayscaleImage(expectedSquare);
            assertEquals(expectedImage,squaredImage);
            assertNotEquals(expectedImage,widerImage);
            assertEquals(expectedImage.getNumColumns(),n);
            assertEquals(expectedImage.getNumRows(),n);
            assertEquals(squaredImage.getNumColumns(),n);
            assertEquals(squaredImage.getNumRows(),n);
            assertEquals(widerImage.getNumColumns(),n);
            assertEquals(widerImage.getNumRows(),n+t);
        }
    }
}
