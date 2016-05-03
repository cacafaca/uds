using Microsoft.VisualStudio.TestTools.UnitTesting;
using Uds.Communication;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Uds.Communication.Tests
{
    [TestClass()]
    public class UtilTests
    {
        [Serializable]
        [XmlInclude(typeof(PersonTest))]
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

            var serializedArray = Util.SerializeMessage(expectedMessage);
            var deserializedMessage = Util.DeserializeMessage(serializedArray);

            Assert.AreEqual(expectedMessage, deserializedMessage);
        }
    }
}