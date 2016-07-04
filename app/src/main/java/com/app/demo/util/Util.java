package com.app.demo.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Abhishek Mukherjee on 4/16/2016.
 */
public class Util {


    Activity mContext;

    public Util(Context paramContext)
    {
        this.mContext = ((Activity)paramContext);
    }

    public static Document getDocument(byte[] paramArrayOfByte)
    {
        try
        {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
            DocumentBuilder localDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            localDocumentBuilder.isValidating();

            return localDocumentBuilder.parse(byteArrayInputStream);

        }
        catch (Exception ex) {}
        return null;
    }

    public boolean isConnectionPossible()
    {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
    }

    public boolean isEmailValid(String paramString)
    {
        boolean bool = false;
        if (Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE).matcher(paramString).matches()) {
            bool = true;
        }
        return bool;
    }

    public void showDialog(int paramInt)
    {
        new AlertDialog.Builder(this.mContext).setMessage(paramInt).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
        }).show();
    }

    public void showDialog(String paramString)
    {
        new AlertDialog.Builder(this.mContext).setMessage(paramString).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
        }).show();
    }

}