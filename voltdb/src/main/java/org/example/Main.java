package org.example;

import org.example.client.VoltdbClient;
import org.example.register.RegisterBalanceProcedure;
import org.example.register.RegisterProcedure;
import org.example.show.GetPackageProcedure;
import org.example.show.ShowPackageAmountSmsProcedure;
import org.example.show.ShowUserInfProcedure;
import org.voltdb.client.Client;
import org.voltdb.client.ProcCallException;
import java.io.IOException;


// MAIN SINIFINDA PROCEDURLERIN KULLANIMI ORNEKLENDIRILMISTIR

// RESOURCES KLASORUNDE PROCEDURES DOSYASINDA PROCEDURLERIN YAPISI MEVCUT

// PROCEDURLER VOLTDB MANAGEMENT CENTERDA OLUSTURULMUSTUR

// RESOURCES KLASORUNDE CREATE_TABLE DOSYASINDA TABLOLARIN YAPISI MEVCUT


public class Main {
    public static void main(String[] args) throws InterruptedException {
        // VoltdbClient sınıfını oluştur
        VoltdbClient voltdbClient = new VoltdbClient();

        try {
            // VoltdbClient sınıfından client'i al
            Client client = voltdbClient.getClient();

            ////// KULLANICI EKLE////////

            RegisterProcedure.execute(client, 2, "5340344358", "Alican", "Cagdas", "alican@cagdas.com", "password");

            ////// PAKET OLUSTUR ////////

            RegisterBalanceProcedure.execute(client,3,1,2,100);

            ////// SUBSC_ID ILE KULLANICIYA PAKET EKLE //////////


            //// KULLANICIYI GOSTER (MSISDN) ILE ///////////

            ShowUserInfProcedure.execute(client, "5340344258");

            //// KULLANICIYI PAKETINI GOSTER (MSISDN) ILE ///////////

            GetPackageProcedure.execute(client, "5340344258");

            //////// KULLANICI AKTIF SMS GORUNTULE //////

            ShowPackageAmountSmsProcedure.execute(client,2);


            //////// KULLANICI SMS BILGISI GUNCELLE ////////



            //////// GUNCEL KULLANICI AKTIF SMS GORUNTULE //////

            ShowPackageAmountSmsProcedure.execute(client,2);



        } catch (IOException | ProcCallException e) {
            e.printStackTrace();
        } finally {
            // VoltdbClient sınıfındaki client'i kapat
            voltdbClient.closeClient();
        }
    }
}
