package com.llm.test;


import com.llm.security.utils.AESCryptUtil;

public class AESCryptUtilTest {

    public static void main(String[] args) {
        testEncrypt();
        testDecrypt();

    }

    public static void testEncrypt() {
        System.out.println(AESCryptUtil.encrypt("{\"commonParams\":{\"token\":\"AcEDAMnRI-CG9uJrx-4_mwM-Sw1_08-H1yx5f533GcuvL4mSWPusa8gtq6FEPfdJxPwTPCTUQsVUfD7FmgOt8dHqN5CIY8ygpYdC5VZ7Ne_Vh0LlVns179WHQuVWezXv1YdC5VZ7Ne_Vh0LlVns179WHQuVWezXv1YdC5VZ7Ne_Vh0LlVns179XanpXOIm7WXC0\",\"version\":\"1.0.0\",\"deviceCode\":\"d38470fd8sahf3\",\"sourceType\":\"3\",\"os\":\"android.5.0\",\"channel\":\"1\"},\"dataParams\":{\"targetType\":\"610001\",\"targetId\":\"3d2270c690654762a29a266113ce313c\"}}"));
    }

    public static void testDecrypt() {
        System.out.println(AESCryptUtil.decrypt("Rg+LPJAjf4zyxdPmxB+TtbpM6JTjcBc7z9MnuImad0ipkcSI6TZ84kBNlIpzAuhjLO82qOWNYIuP\n" +
                "/8nOSK6K8w=="));
    }
}
