/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author LPENAF
 */
public class Serializing {

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream oss = new ObjectOutputStream(output);
        oss.writeObject(obj);
        return output.toByteArray();
    }
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        ObjectInputStream iss = new ObjectInputStream(input);
        return iss.readObject();
    }
}