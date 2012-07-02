/**
 * Copyright (C) 2011 The XDocReport Team <xdocreport@googlegroups.com>
 *
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.opensagres.xdocreport.document.images;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import fr.opensagres.xdocreport.core.document.ImageFormat;

public class FileImageProviderTestCase
{

    @Test
    public void loadPNGWithoutUsingImageSize()
        throws Exception
    {
        IImageProvider imageProvider =
            new FileImageProvider( new File( "src/test/resources/fr/opensagres/xdocreport/document/images/logo.png" ) );
        Assert.assertEquals( ImageFormat.png, imageProvider.getImageFormat() );
        Assert.assertNull( imageProvider.getWidth() );
        Assert.assertNull( imageProvider.getHeight() );
    }

    @Test
    public void loadPNGAndUseImageSize()
        throws Exception
    {
        boolean useImageSize = true;
        IImageProvider imageProvider =
            new FileImageProvider( new File( "src/test/resources/fr/opensagres/xdocreport/document/images/logo.png" ),
                                   useImageSize );
        Assert.assertEquals( ImageFormat.png, imageProvider.getImageFormat() );
        Assert.assertEquals( 220f, imageProvider.getWidth().floatValue(), 0 );
        Assert.assertEquals( 200f, imageProvider.getHeight().floatValue(), 0 );
    }

    @Test
    public void loadPNGAndModifyUseImageSize()
        throws Exception
    {
        // Load image
        boolean useImageSize = true;
        IImageProvider imageProvider =
            new FileImageProvider( new File( "src/test/resources/fr/opensagres/xdocreport/document/images/logo.png" ),
                                   useImageSize );
        Assert.assertEquals( ImageFormat.png, imageProvider.getImageFormat() );
        Assert.assertNotNull( imageProvider.getWidth() );
        Assert.assertEquals( 220f, imageProvider.getWidth().floatValue(), 0 );
        Assert.assertNotNull( imageProvider.getHeight() );
        Assert.assertEquals( 200f, imageProvider.getHeight().floatValue(), 0 );

        // Don't use width/height from the image
        imageProvider.setUseImageSize( false );
        Assert.assertEquals( ImageFormat.png, imageProvider.getImageFormat() );
        Assert.assertNull( imageProvider.getWidth() );
        Assert.assertNull( imageProvider.getHeight() );

        // Use width/height from the image
        imageProvider.setUseImageSize( true );
        Assert.assertEquals( ImageFormat.png, imageProvider.getImageFormat() );
        Assert.assertNotNull( imageProvider.getWidth() );
        Assert.assertEquals( 220f, imageProvider.getWidth().floatValue(), 0 );
        Assert.assertNotNull( imageProvider.getHeight() );
        Assert.assertEquals( 200f, imageProvider.getHeight().floatValue(), 0 );
    }

    @Test
    public void loadPNGWithoutUsingImageSizeAndForceWidth()
        throws Exception
    {
        IImageProvider imageProvider =
            new FileImageProvider( new File( "src/test/resources/fr/opensagres/xdocreport/document/images/logo.png" ) );
        imageProvider.setWidth( 100f );
        Assert.assertEquals( ImageFormat.png, imageProvider.getImageFormat() );
        Assert.assertNotNull( imageProvider.getWidth() );
        Assert.assertEquals( 100f, imageProvider.getWidth().floatValue(), 0 );
        Assert.assertNull( imageProvider.getHeight() );
    }

    @Test
    public void loadPNGByUsingImageSizeAndForceWidth()
        throws Exception
    {
        boolean useImageSize = true;
        IImageProvider imageProvider =
            new FileImageProvider( new File( "src/test/resources/fr/opensagres/xdocreport/document/images/logo.png" ),
                                   useImageSize );
        imageProvider.setWidth( 100f );
        Assert.assertEquals( ImageFormat.png, imageProvider.getImageFormat() );
        Assert.assertNotNull( imageProvider.getWidth() );
        Assert.assertEquals( 100f, imageProvider.getWidth().floatValue(), 0 );
        Assert.assertNotNull( imageProvider.getHeight() );
        Assert.assertEquals( 200f, imageProvider.getHeight().floatValue(), 0 );
    }

    @Test
    public void loadPNGByUsingImageSizeAndForceWidthAndUseRatio()
        throws Exception
    {
        boolean useImageSize = true;
        IImageProvider imageProvider =
            new FileImageProvider( new File( "src/test/resources/fr/opensagres/xdocreport/document/images/logo.png" ),
                                   useImageSize );
        imageProvider.setResize( true );
        imageProvider.setWidth( 100f );
        Assert.assertEquals( ImageFormat.png, imageProvider.getImageFormat() );
        Assert.assertNotNull( imageProvider.getWidth() );
        Assert.assertEquals( 100f, imageProvider.getWidth().floatValue(), 0 );
        // Here height is computed
        Assert.assertNotNull( imageProvider.getHeight() );
        Assert.assertEquals( 91f, imageProvider.getHeight().floatValue(), 0 );
    }

    @Test
    public void loadPNGByAndChangeResize()
        throws Exception
    {
        boolean useImageSize = true;
        IImageProvider imageProvider =
            new FileImageProvider( new File( "src/test/resources/fr/opensagres/xdocreport/document/images/logo.png" ),
                                   useImageSize );
        imageProvider.setResize( true );
        imageProvider.setWidth( 100f );
        Assert.assertEquals( ImageFormat.png, imageProvider.getImageFormat() );
        Assert.assertNotNull( imageProvider.getWidth() );
        Assert.assertEquals( 100f, imageProvider.getWidth().floatValue(), 0 );
        // Here height is computed
        Assert.assertNotNull( imageProvider.getHeight() );
        Assert.assertEquals( 91f, imageProvider.getHeight().floatValue(), 0 );

        // Use the original height
        imageProvider.setResize( false );
        Assert.assertNotNull( imageProvider.getHeight() );
        Assert.assertEquals( 200f, imageProvider.getHeight().floatValue(), 0 );

        // Use the computed height
        imageProvider.setResize( true );
        Assert.assertNotNull( imageProvider.getHeight() );
        Assert.assertEquals( 91f, imageProvider.getHeight().floatValue(), 0 );

        // Use null height
        imageProvider.setUseImageSize( false );
        Assert.assertNull( imageProvider.getHeight() );

        // Use the computed height
        imageProvider.setUseImageSize( true );
        Assert.assertNotNull( imageProvider.getHeight() );
        Assert.assertEquals( 91f, imageProvider.getHeight().floatValue(), 0 );

        // Force height and compute width.
        imageProvider.setSize( null, 1000f );
        Assert.assertNotNull( imageProvider.getWidth() );
        Assert.assertEquals( 1100f, imageProvider.getWidth().floatValue(), 0 );
        Assert.assertNotNull( imageProvider.getHeight() );
        Assert.assertEquals( 1000f, imageProvider.getHeight().floatValue(), 0 );
    }
}
