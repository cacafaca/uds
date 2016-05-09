using Microsoft.VisualStudio.TestTools.UnitTesting;
using Uds.Communication;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;
using System.Runtime.Serialization;

namespace Uds.Communication.Tests
{
    [TestClass()]
    public class UtilTests
    {
        [Serializable]
        public class PersonTest
        {
            public string Name;
            public int Age;
        }

        [TestMethod()]
        public void SerializeDeserializeMessageTest()
        {
            Message expectedMessage = new Message()
            {
                From = new System.Net.IPEndPoint(Util.GetLocalIPAddress(), 10000),
                To = new System.Net.IPEndPoint(Util.GetLocalIPAddress(), 10001),
                Body = new PersonTest() { Name = "Aleksandar Vucic", Age = 45 },
                Id = 123,
                MessageType = MessageType.System
            };

            var serializedStream = Uds.Common.Serialize.SerializeToXmlStream(expectedMessage);
            var deserializedMessage = Uds.Common.Serialize.DeserializeFromXmlStream(serializedStream, typeof(Message));

            Assert.AreEqual(expectedMessage, deserializedMessage);
        }
    }
}