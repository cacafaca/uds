using Microsoft.VisualStudio.TestTools.UnitTesting;
using Communication;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;

namespace Communication.Tests
{
    [TestClass()]
    public class ConnectionTests
    {
        [TestMethod()]
        public void ListenAndAcceptTest()
        {
            IPHostEntry ipHostInfo = Dns.GetHostEntry(Dns.GetHostName());
            IPAddress ipAddress = ipHostInfo.AddressList.Where(adr => adr.AddressFamily == System.Net.Sockets.AddressFamily.InterNetwork).First();
            if (ipAddress != null)
            {
                IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 11000);

                Connection connection = new Connection();
                connection.Listen(localEndPoint);

                Socket sender = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                sender.Connect(localEndPoint);
                byte[] expectedData = new byte[5] { 1, 2, 3, 4, 5 };
                sender.Send(expectedData);

                Socket receiver = connection.Accept();
                byte[] receivedData = new byte[256];
                int receivedCount = receiver.Receive(receivedData);

                Assert.AreEqual(expectedData.Length, receivedCount, "Size is different.");
                for (int i = 0; i < receivedCount; i++)
                    Assert.AreEqual(expectedData[i], receivedData[i], "Data are not the same.");
            }
            else
                Assert.Fail("Can't obtain IP address.");
        }

        [TestMethod()]
        public void ListenTest()
        {
            IPHostEntry ipHostInfo = Dns.GetHostEntry(Dns.GetHostName());
            IPAddress ipAddress = ipHostInfo.AddressList.Where(adr => adr.AddressFamily == System.Net.Sockets.AddressFamily.InterNetwork).First();
            if (ipAddress != null)
            {
                IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 11000);

                Connection connection = new Connection();
                connection.Listen(localEndPoint);

                byte[] expectedData = new byte[5] { 1, 2, 3, 4, 5 };
                new Task(() =>
                   {
                       Socket sender = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                       System.Threading.Thread.Sleep(1000);
                       sender.Connect(localEndPoint);
                       sender.Send(expectedData);
                   }).Start();

                Socket receiver = connection.Accept();
                byte[] receivedData = new byte[256];
                int receivedCount = receiver.Receive(receivedData);

                Assert.AreEqual(expectedData.Length, receivedCount, "Size is different.");
                for (int i = 0; i < receivedCount; i++)
                    Assert.AreEqual(expectedData[i], receivedData[i], "Data are not the same.");
            }
            else
                Assert.Fail("Can't obtain IP address.");
        }
    }
}