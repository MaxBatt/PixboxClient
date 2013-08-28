/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package de.pixbox.client;

public final class R {
    public static final class attr {
        /** <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>no_cache</code></td><td>0</td><td></td></tr>
<tr><td><code>memory</code></td><td>1</td><td></td></tr>
<tr><td><code>disk</code></td><td>2</td><td></td></tr>
</table>
         */
        public static final int cache_mode=0x7f010000;
        /** <p>Must be a floating point value, such as "<code>1.2</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static final int memoryPercentToUse=0x7f010001;
    }
    public static final class dimen {
        /**  Default screen margins, per the Android Design guidelines. 

         Customize dimensions originally defined in res/values/dimens.xml (such as
         screen margins) for sw720dp devices (e.g. 10" tablets) in landscape here.
    
         */
        public static final int activity_horizontal_margin=0x7f050000;
        public static final int activity_vertical_margin=0x7f050001;
    }
    public static final class drawable {
        public static final int arnie=0x7f020000;
        public static final int border=0x7f020001;
        public static final int ic_launcher=0x7f020002;
    }
    public static final class id {
        public static final int action_settings=0x7f040010;
        public static final int disk=0x7f040002;
        public static final int editUsername=0x7f04000d;
        public static final int galleryBtn=0x7f040009;
        public static final int imgView=0x7f040003;
        public static final int mainContent=0x7f040006;
        public static final int memory=0x7f040001;
        public static final int no_cache=0x7f040000;
        public static final int okBtn=0x7f04000e;
        public static final int progressBar1=0x7f04000c;
        public static final int takePictureBtn=0x7f040008;
        public static final int textView1=0x7f040005;
        public static final int tvError=0x7f04000b;
        public static final int tvUploadInfo=0x7f040004;
        public static final int tvUserResult=0x7f04000f;
        public static final int tvUserWelcome=0x7f040007;
        public static final int uploadImgBtn=0x7f04000a;
    }
    public static final class layout {
        public static final int gallery_activity=0x7f030000;
        public static final int image_row=0x7f030001;
        public static final int main_activity=0x7f030002;
        public static final int register_activity=0x7f030003;
    }
    public static final class menu {
        public static final int main=0x7f080000;
    }
    public static final class string {
        public static final int action_settings=0x7f060001;
        public static final int add_photo_dialog=0x7f06000b;
        public static final int app_name=0x7f060000;
        public static final int back=0x7f060012;
        public static final int btn_upload_img=0x7f06000f;
        public static final int cancel=0x7f06000e;
        public static final int err_connect=0x7f060007;
        public static final int err_general=0x7f060003;
        public static final int err_no_internet=0x7f060010;
        public static final int err_no_username=0x7f060004;
        public static final int err_upload=0x7f060006;
        public static final int err_wrong_username=0x7f060005;
        public static final int gallery_btn=0x7f06000a;
        public static final int hello_world=0x7f060002;
        public static final int no_images=0x7f060011;
        public static final int picture_cam=0x7f06000c;
        public static final int picture_gallery=0x7f06000d;
        public static final int take_picture_btn=0x7f060009;
        public static final int uploaded_label=0x7f060013;
        public static final int user_welcome_text=0x7f060008;
    }
    public static final class style {
        /** 
        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.
    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.
        

        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.
    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 
         */
        public static final int AppBaseTheme=0x7f070000;
        /**  Application theme. 
 All customizations that are NOT specific to a particular API-level can go here. 
         */
        public static final int AppTheme=0x7f070001;
    }
    public static final class styleable {
        /** Attributes that can be used with a WebCachedImageView.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #WebCachedImageView_cache_mode de.pixbox.client:cache_mode}</code></td><td></td></tr>
           <tr><td><code>{@link #WebCachedImageView_memoryPercentToUse de.pixbox.client:memoryPercentToUse}</code></td><td></td></tr>
           </table>
           @see #WebCachedImageView_cache_mode
           @see #WebCachedImageView_memoryPercentToUse
         */
        public static final int[] WebCachedImageView = {
            0x7f010000, 0x7f010001
        };
        /**
          <p>This symbol is the offset where the {@link de.pixbox.client.R.attr#cache_mode}
          attribute's value can be found in the {@link #WebCachedImageView} array.


          <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>no_cache</code></td><td>0</td><td></td></tr>
<tr><td><code>memory</code></td><td>1</td><td></td></tr>
<tr><td><code>disk</code></td><td>2</td><td></td></tr>
</table>
          @attr name android:cache_mode
        */
        public static final int WebCachedImageView_cache_mode = 0;
        /**
          <p>This symbol is the offset where the {@link de.pixbox.client.R.attr#memoryPercentToUse}
          attribute's value can be found in the {@link #WebCachedImageView} array.


          <p>Must be a floating point value, such as "<code>1.2</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          @attr name android:memoryPercentToUse
        */
        public static final int WebCachedImageView_memoryPercentToUse = 1;
    };
}
