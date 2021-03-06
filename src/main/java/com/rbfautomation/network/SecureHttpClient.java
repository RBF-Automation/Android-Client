package com.rbfautomation.network;

import android.content.Context;

import com.rbfautomation.R;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import java.io.InputStream;
import java.security.KeyStore;

public class SecureHttpClient extends DefaultHttpClient {

    private final Context mContext;

    private static SecureHttpClient mHttpClient = null;
    private static final String BKS = "BKS";
    private static final String HTTPS = "https";


    public static SecureHttpClient getSecureHttpClient(Context context) {
        if( mHttpClient == null){
            mHttpClient = new SecureHttpClient(context);
        }
        return mHttpClient;
    }


    private SecureHttpClient(Context context) {
        mContext = context;
    }

    @Override
    protected ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme(HTTPS, newSslSocketFactory(), Credentials.PORT));
        return new SingleClientConnManager(getParams(), registry);
    }

    private SSLSocketFactory newSslSocketFactory() {
        try {
            KeyStore trusted = KeyStore.getInstance(BKS);
            InputStream in = mContext.getResources().openRawResource(R.raw.keystore);
            try {
                trusted.load(in, Credentials.KEYSTORE_PASSWORD.toCharArray());
            } finally {
                in.close();
            }
            SSLSocketFactory sf = new SSLSocketFactory(trusted);
            sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            return sf;
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}