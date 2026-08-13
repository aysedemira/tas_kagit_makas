import java.util.Random;
import java.util.Scanner;

public class TasKagitMakas {

    // Enum kullanımı
    enum Secim {
        TAS, KAGIT, MAKAS
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println(" Taş kağıt makas");

        boolean tekrarOyna = true;

        while (tekrarOyna) {
           
            System.out.print("\nKaç tur oynamak istersiniz? (3 veya 5): ");
            int hedefTur = scanner.nextInt();

            int oyuncuSonuc = 0;
            int bilgisayarSonuc = 0;
            int beraberlikSayisi = 0;
            int mevcutTur = 1;

            while (mevcutTur <= hedefTur) {
                System.out.println("\n--- TUR " + mevcutTur + " / " + hedefTur + " ---");
                System.out.println("1- Taş");
                System.out.println("2- Kağıt");
                System.out.println("3- Makas");
                System.out.print("Seçiminiz (1-3): ");
                
                int secimNo = scanner.nextInt();

              
                if (secimNo < 1 || secimNo > 3) {
                    System.out.println(" HATA Yanlış seçim yaptınız! Lütfen sadece 1, 2 veya 3 girin.");
                    continue; 
                }

                
                Secim oyuncuSecimi = numarayiSecimeCevir(secimNo);

                
                int rastgeleRakam = random.nextInt(3) + 1;
                Secim bilgisayarSecimi = numarayiSecimeCevir(rastgeleRakam);

                System.out.println("Sizin Seçiminiz     : " + oyuncuSecimi);
                System.out.println("Bilgisayarın Seçimi: " + bilgisayarSecimi);

              
                if (oyuncuSecimi == bilgisayarSecimi) {
                    System.out.println("Sonuç: Berabere!");
                    beraberlikSayisi++;
                } else if ((oyuncuSecimi == Secim.TAS && bilgisayarSecimi == Secim.MAKAS) ||
                           (oyuncuSecimi == Secim.KAGIT && bilgisayarSecimi == Secim.TAS) ||
                           (oyuncuSecimi == Secim.MAKAS && bilgisayarSecimi == Secim.KAGIT)) {
                    System.out.println("Sonuç: Bu turu Kazandınız!");
                    oyuncuSonuc++;
                } else {
                    System.out.println("Sonuç: Bu turu Kaybettiniz!");
                    bilgisayarSonuc++;
                }

                System.out.println("Sonuc -> Siz: " + oyuncuSonuc + " | Bilgisayar: " + bilgisayarSonuc);
                
            
                mevcutTur++; 
            }

            
            System.out.println("\n...............................");
            System.out.println("        Oyun sonucu              ");
            System.out.println(".................................");
            if (oyuncuSonuc > bilgisayarSonuc) {
                System.out.println("TEBRİKLER! Oyunu siz kazandınız! ");
            } else if (bilgisayarSonuc > oyuncuSonuc) {
                System.out.println("MALESEF! Oyunu bilgisayar kazandı. ");
            } else {
                System.out.println("Oyun berabere bitti! ");
            }

          
            istatistikleriGoster(hedefTur, oyuncuSonuc, bilgisayarSonuc, beraberlikSayisi);

            // Tekrar Oynama ıstegı sorgulama
            System.out.print("\nTekrar oynamak ister misiniz? (Evet için 1, Çıkış için 0): ");
            int cevap = scanner.nextInt();
            if (cevap != 1) {
                tekrarOyna = false;
                System.out.println("Oyun kapatılıyor, iyi günler!");
            }
        }

        scanner.close();
    }

    
    public static Secim numarayiSecimeCevir(int no) {
        switch (no) {
            case 1:
                return Secim.TAS;
            case 2:
                return Secim.KAGIT;
            case 3:
                return Secim.MAKAS;
            default:
                return Secim.TAS;
        }
    }

    ///GEnel tablo
    public static void istatistikleriGoster(int toplamTur, int galibiyet, int maglubiyet, int beraberlik) {
        double galibiyetYuzdesi = ((double) galibiyet / toplamTur) * 100;
        
        System.out.println("\n Genel tablo");
        System.out.println("Toplam Tur        : " + toplamTur);
        System.out.println("Kazanılan Tur     : " + galibiyet);
        System.out.println("Kaybedilen Tur    : " + maglubiyet);
        System.out.println("Berabere Tur      : " + beraberlik);
        System.out.printf("Galibiyet Yüzdesi : %%.2f%%\n", galibiyetYuzdesi);
    }
}
