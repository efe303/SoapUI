package api_yandex_maps;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class Utils {

//    Utils.drawPicture(response.body().asString().getBytes(StandardCharsets.US_ASCII));
    public static void drawPicture(byte[] bytes) {
        //TODO
//        final JOptionPane optionPane = new JOptionPane(".....",
//                JOptionPane.INFORMATION_MESSAGE,
//                JOptionPane.DEFAULT_OPTION,
//                null,
//                new Object[] {},
//                null);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(bytes));
        label.setSize(200, 200);


        final JDialog dialog = new JDialog();
//        dialog.setTitle(".....");
        dialog.setAlwaysOnTop(true);
        dialog.setContentPane(label);
//        dialog.setSize(200, 360);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setFocusable(false);
//        optionPane.setIcon(new ImageIcon(bytes));
        dialog.setVisible(true);
        dialog.dispose();

//        final BufferedImage image = ImageIO.read(new ByteArrayInputStream());
//        ImageIO.write(image, "png", optionPane.);
    }

    public static void savePicture(byte[] bytes) throws Exception {
        try (FileOutputStream fos = new FileOutputStream("d:\\img.png")) {
            fos.write(bytes);
            //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
        }
    }
}
