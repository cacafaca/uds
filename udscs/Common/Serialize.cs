using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Uds.Common
{
    public class Serialize<T>
    {
        private static bool IsSerializable()
        {
            return typeof(T).IsDefined(typeof(SerializableAttribute), false);
        }

        #region Xml

        public static Stream SerializeToXmlStream(T serializableObject)
        {
            if (serializableObject != null)
            {
                if (IsSerializable())
                {
                    XmlSerializer xmlSerializer = new XmlSerializer(typeof(T));
                    MemoryStream memoryStream = new MemoryStream();
                    xmlSerializer.Serialize(memoryStream, serializableObject);
                    memoryStream.Position = 0;
                    return memoryStream;
                }
                else
                    throw new ArgumentException("Object is not serializable.");
            }
            else
                throw new ArgumentNullException();
        }

        public static byte[] SerializeToXmlArray(T serializableObject)
        {
            return ((MemoryStream)SerializeToXmlStream(serializableObject)).ToArray();
        }

        public static T DeserializeFromXmlStream(Stream receivedData)
        {
            if (receivedData != null)
            {
                if (IsSerializable())
                {
                    XmlSerializer xmlSerializer = new XmlSerializer(typeof(T));
                    T deserializedObject = (T)xmlSerializer.Deserialize(receivedData);
                    return deserializedObject;
                }
                else
                    throw new ArgumentException("Object is not deserializable.");
            }
            else
                throw new ArgumentNullException();
        }

        public static T DeserializeFromXmlArray(byte[] receivedData)
        {
            return DeserializeFromXmlStream(new MemoryStream(receivedData));
        }

        #endregion Xml

        public static Stream SerializeToBinaryFormatStream(T serializableObject)
        {
            if (serializableObject != null)
            {
                if (IsSerializable())
                {
                    BinaryFormatter binaryFormatter = new BinaryFormatter();
                    MemoryStream memoryStream = new MemoryStream();
                    //xmlSerializer.Serialize(memoryStream, serializableObject);
                    memoryStream.Position = 0;
                    return memoryStream;
                }
                else
                    throw new ArgumentException("Object is not serializable.");
            }
            else
                throw new ArgumentNullException();
        }

    }
}
