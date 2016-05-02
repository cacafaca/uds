using Microsoft.VisualStudio.TestTools.UnitTesting;
using Uds.Communication;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Uds.Communication.Tests
{
    [TestClass()]
    public class UtilTests
    {
        [TestMethod()]
        public void SerializeDeserializeMessageTest()
        {
            Message expectedMessage = new Message()
            {
                From = new System.Net.IPEndPoint(Util.GetLocalIPAddress(), 10000),
                To = new System.Net.IPEndPoint(Util.GetLocalIPAddress(), 10001),
                Body = "Test body",
                Id = 123,
                MessageType = MessageType.System
            };

            var serializedMessage = Util.SerializeMessage(expectedMessage);
            var deserializedMessage = Util.DeserializeMessage(serializedMessage);

            Assert.AreEqual(expectedMessage, deserializedMessage);
            Assert.Fail();
        }
    }
}