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
    public class Serialize
    {
        private static bool IsSerializable(Type type)
        {
            if (type != null)
                return type.IsDefined(typeof(SerializableAttribute), false);
            else
                throw new ArgumentNullException();
        }

        #region Xml

        public static Stream SerializeToXmlStream(object serializableObject)
        {
            if (serializableObject != null)
            {
                if (IsSerializable(serializableObject.GetType()))
                {
                    XmlSerializer xmlSerializer = new XmlSerializer(serializableObject.GetType());
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

        public static byte[] SerializeToXmlArray(object serializableObject)
        {
            return ((MemoryStream)SerializeToXmlStream(serializableObject)).ToArray();
        }

        public static object DeserializeFromXmlStream(Stream receivedData, Type type)
        {
            if (receivedData != null)
            {
                if (IsSerializable(type))
                {
                    XmlSerializer xmlSerializer = new XmlSerializer(type);
                    object deserializedObject = xmlSerializer.Deserialize(receivedData);
                    return deserializedObject;
                }
                else
                    throw new ArgumentException("Object is not deserializable.");
            }
            else
                throw new ArgumentNullException();
        }

        public static object DeserializeFromXmlArray(byte[] receivedData, Type type)
        {
            return DeserializeFromXmlStream(new MemoryStream(receivedData), type);
        }

        #endregion Xml

        public static Stream SerializeToBinaryFormatStream(object serializableObject, Type type)
        {
            if (serializableObject != null)
            {
                if (IsSerializable(type))
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
