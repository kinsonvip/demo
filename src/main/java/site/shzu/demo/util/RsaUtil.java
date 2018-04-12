package site.shzu.demo.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * @Author: Kinson
 * @Description: RSA加密工具类
 * @Date: Created in 2018/04/12 13:37
 * @Version: 1.0
 */
public class RsaUtil {
    //公钥
    private static final String publicKeyBase64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMHRRR9zLJvrJnFreoMg16zApctGxPHQEZF1fSDqvpmw/UPQTzEewG8BsPfagzWps70t2qhmfkCrjxnH2Rbcj5zvZocyF/hWHXDN8g1mQYzm4+4XLlbhnRXiUsNbKtwKyAQQSjshF4qA7J3y8KmyfekjpJaFR1+AJ2BKMT52Rh0QIDAQAB";

    //私钥
    private static final String privateKeyBase64 = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIwdFFH3Msm+smcWt6gyDXrMCly0bE8dARkXV9IOq+mbD9Q9BPMR7AbwGw99qDNamzvS3aqGZ+QKuPGcfZFtyPnO9mhzIX+FYdcM3yDWZBjObj7hcuVuGdFeJSw1sq3ArIBBBKOyEXioDsnfLwqbJ96SOkloVHX4AnYEoxPnZGHRAgMBAAECgYBBJ9SnORj5ECDOJ5ZINN5n2DKJYa1sLMSx8lT4bbIr89usiqOelRr5LvamgXySRPQjIa/K+K2fMg1G7crGshLexkcuRG4Tx1l2iXrR/5QcNWqSoaUe5NFdalRW2EcdrYg128wBDirs0TzcXR2JNk3K4cd576D8M8yafHwZOnMOcQJBANjm78yT3TKNK5KoQkhZbwFJ16s4xIq11ts/Tahfcm3AUyyVMV8OhIkSRlapqr1/DMjtWtjv156e8uT79w0pCGMCQQClXrIta+5qgipcVRSQV6SlznyuNyrItbWIXCpzIlO2VGi4Wujhk9AIdcwPPeu+sDwzqLJJ81HvipHupFfVQbE7AkBP66foJYOXoNSxUTY7GlkAjIjfGiexRJdKaeTfaWvsbLU49DAg6mSDbSxlulajRDEdx/d1f8pfNAojvYOmYbwpAkEAgHD6hxjrhsIu0KQsYqgP89BbOp0Sl+26X3zmY6Jnzpfk9jt14a8Cx29m3PfFIMiVGbKPN6zLGPhRvD7dEYACfQJBAI/p3T9+Yr+2BHdQ7XFUCoHqk1CX0spRP8Vu3PBdMQ67MqNpDMoH1a05drAdNEEp6ox3fJ6HOIPDNM7v/WpcS2Y=";

    RSA rsa = new RSA(privateKeyBase64,publicKeyBase64);

    /**
     * 得到加密后的密文
     * @param str 要加密的密文
     * @return 加密后的密文
     */
    public String getEncryptStr(String str){
        return rsa.encryptStr(str, KeyType.PublicKey, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 得到解密后的密文
     * @param str 要解密的密文
     * @return 解密后的密文
     */
    public String getDecryptStr(String str){
        return rsa.decryptStr(str,KeyType.PrivateKey,CharsetUtil.CHARSET_UTF_8);
    }
}
