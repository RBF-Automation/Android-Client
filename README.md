

HOW TO SSL (ssl setup guide)
----------


This guide is based off of this guide: http://blog.antoine.li/2010/10/22/android-trusting-ssl-certificates/


###Creating the keystore

This assumes you have your SSL.crt from the web server

Download BouncyCastle Provider: http://bouncycastle.org/download/bcprov-jdk16-145.jar

generate the keystore


    keytool -importcert -v -trustcacerts -file "path_to_cert/interm_ca.cer" -alias IntermediateCA -keystore "keystore.bks" -provider org.bouncycastle.jce.provider.BouncyCastleProvider -providerpath "path_to_bouncycastle/bcprov-jdk16-145.jar" -storetype BKS -storepass YOUR_PASSWORD

Verify the key is in the keystore

    keytool -list -keystore "keystore.bks" -provider org.bouncycastle.jce.provider.BouncyCastleProvider -providerpath "bcprov-jdk16-145.jar" -storetype BKS -storepass YOUR_PASSWORD

Put the keystore in /res/raw/keystore.bsk


NOTES:

    {
        "id": 123,
        "type": 0,
        "name": "name",
        "off": "off",
        "on": "on"
    }
