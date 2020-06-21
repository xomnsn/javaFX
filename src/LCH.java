import javafx.scene.paint.Color;

public class LCH {

    public static Color colorFromLCH(double cieL, double cieC, double cieH) {
        // CIE-LCH -> CIE-Lab
        // http://www.easyrgb.com/en/math.php
        double cieHradians = cieH * Math.PI / 180;
        double ciea = Math.cos(cieHradians) * cieC;
        double cieb = Math.sin(cieHradians) * cieC;

        // CIE-Lab -> XYZ
        double varY = (cieL + 16) / 116;
        double varX = ciea / 500 + varY;
        double varZ = varY - cieb / 200;

        if (varY * varY * varY > 0.008856)
            varY = varY * varY * varY;
        else
            varY = (varY - 16. / 116) / 7.787;
        if (varX * varX * varX > 0.008856)
            varX = varX * varX * varX;
        else
            varX = (varX - 16. / 116) / 7.787;
        if (varZ * varZ * varZ > 0.008856)
            varZ = varZ * varZ * varZ;
        else
            varZ = (varZ - 16. / 116) / 7.787;

        double X = varX * 95.047;
        double Y = varY * 100;
        double Z = varZ * 108.883;

        //XYZ -> sRGB
        varX = X / 100;
        varY = Y / 100;
        varZ = Z / 100;

        double varR = varX * 3.2406 + varY * -1.5372 + varZ * -0.4986;
        double varG = varX * -0.9689 + varY * 1.8758 + varZ * 0.0415;
        double varB = varX * 0.0557 + varY * -0.2040 + varZ * 1.0570;

        if (varR > 0.0031308)
            varR = 1.055 * Math.pow(varR, 1 / 2.4) - 0.055;
        else
            varR = 12.92 * varR;
        if (varG > 0.0031308)
            varG = 1.055 * Math.pow(varG, 1 / 2.4) - 0.055;
        else
            varG = 12.92 * varG;
        if (varB > 0.0031308)
            varB = 1.055 * Math.pow(varB, 1 / 2.4) - 0.055;
        else
            varB = 12.92 * varB;

        if (varR < 0)
            varR = 0;
        if (varR > 1)
            varR = 1;
        if (varG < 0)
            varG = 0;
        if (varG > 1)
            varG = 1;
        if (varB < 0)
            varB = 0;
        if (varB > 1)
            varB = 1;

        return Color.color(varR, varG, varB);
    }
}