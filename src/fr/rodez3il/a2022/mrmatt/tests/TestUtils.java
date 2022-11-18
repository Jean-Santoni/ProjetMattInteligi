package fr.rodez3il.a2022.mrmatt.tests;

import java.lang.reflect.Field;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

/**
 * Fonctions vaudoues démoniaque
 * 
 * @author proussille
 *
 *         Ces fonctions cassent (volontairement) tous les principes d'OOP vus
 *         en cours et sont de ce fait VOLONTAIREMENT non documentées et mal
 *         écrites. ;)
 */

public class TestUtils {

  private static class Machin {

    Object voldemort;

    public Machin(Object harryPotter) {
      voldemort = harryPotter;
    }

    public char znoik(int i) {
      return voldemort.toString().charAt(i);
    }
  }

  private static int balancoire(String e) {
    return "RocherMOIHerbePATRICKJoueurOSCOURPommeOMEGAVideMMurObjetPlateau".indexOf(e);
  }

  private static String zarbi(Object o) {
    StringBuilder sb = new StringBuilder();
    Machin magique = new TestUtils.Machin(o);
    for (int i = 0; i < o.toString().length(); i++) {
      char c = magique.znoik(i);
      if (c >= 97 && c <= 109)
        c += '\r';
      else if (c >= 65 && c <= 77)
        c += '\r';
      else if (c >= 110 && c <= 122)
        c -= '\r';
      else if (c >= 78 && c <= 90)
        c -= '\r';
      sb.append(c);
    }
    return sb.toString();
  }

  private static Object omegazogPlusFort(Object hem, Object houila) {
    Object ambulance;
    try {
      Field fd = hem.getClass().getDeclaredField(zarbi(houila));
      boolean CEST_PATRICK = fd.canAccess(hem);
      fd.setAccessible(true);
      ambulance = fd.get(hem);
      fd.setAccessible(CEST_PATRICK);
    } catch (Exception e) {
      return null;
    }
    return ambulance;
  }

  public static String zouzouf(Niveau n) {
    StringBuilder sb = new StringBuilder();
    try {
      Object Object_[][] = (Object[][]) omegazogPlusFort(n, "cyngrnh");
      for (int i = 0; i < Object_.length; i++) {
        for (int j = 0; j < Object_[i].length; j++) {
          sb.append((char) (66 + balancoire(Object_[i][j].getClass().getSimpleName())));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return sb.toString();
  }

  public static String superSAUCISSE(Niveau n) {
    StringBuilder sb = new StringBuilder();
    try {
      Object STRing[][] = (Object[][]) omegazogPlusFort(n, "cyngrnh");
      sb.append((char) (65 + STRing.length));
      for (int i = 0; i < STRing.length; i++)
        sb.append((char) (97 + STRing[i].length));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return sb.toString();
  }
}
