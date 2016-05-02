using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Xml.Serialization;
using System.IO;

namespace Uds.Communication
{
    public static class Util
    {
        public static IPAddress GetLocalIPAddress()
        {
            return Dns.GetHostEntry(Dns.GetHostName()).AddressList.Where(adr => adr.AddressFamily == System.Net.Sockets.AddressFamily.InterNetwork).First();
        }

        public static Message DeserializeMessage(byte[] receivedData)
        {
            XmlSerializer xmlSerializer = new XmlSerializer(typeof(Message));
            MemoryStream memoryStream = new MemoryStream(receivedData);
            Message message = (Message)xmlSerializer.Deserialize(memoryStream);
            return message;
        }

        public static byte[] SerializeMessage(Message message)
        {
            XmlSerializer xmlSerializer = new XmlSerializer(typeof(Message));
            MemoryStream memoryStream = new MemoryStream();
            xmlSerializer.Serialize(memoryStream, message);
            byte[] serializedMessage = memoryStream.ToArray();
            return serializedMessage;
        }
    }
}
