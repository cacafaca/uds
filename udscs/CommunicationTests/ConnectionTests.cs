using Microsoft.VisualStudio.TestTools.UnitTesting;
using Uds.Communication;
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
            IPAddress ipAddress = Util.GetLocalIPAddress();
            if (ipAddress != null)
            {
                IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 11000);

                Connection localConnection = new Connection();
                localConnection.Listen(localEndPoint);

                Socket remoteSender = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                remoteSender.Connect(localEndPoint);

                System.Diagnostics.Stopwatch sw = System.Diagnostics.Stopwatch.StartNew();
                var receiver = localConnection.Accept();
                var elapsed = sw.Elapsed;

                remoteSender.Close();
                localConnection.Close();

                System.Diagnostics.Debug.WriteLine("Time took to accept connection " + elapsed);
                Assert.IsTrue(elapsed.Seconds < 1, "New connection is not accepted in timely manner fashon.");
            }
            else
                Assert.Fail("Can't obtain IP address.");
        }

        [TestMethod()]
        public void Receive_Less_Then_ReadBufferSize_Bytes_Test()
        {
            IPAddress ipAddress = Util.GetLocalIPAddress();
            if (ipAddress != null)
            {
                IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 11000);

                Connection localConnection = new Connection();
                localConnection.Listen(localEndPoint);

                byte[] expectedData = new byte[localConnection.ReadBufferSize - 1];
                for (int i = 0; i < expectedData.Length; i++)
                    expectedData[i] = (byte)(i % byte.MaxValue);

                new Task(() =>
                   {
                       Socket sender = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                       System.Threading.Thread.Sleep(1000);
                       sender.Connect(localEndPoint);
                       sender.Send(expectedData);
                   }).Start();

                var receiver = localConnection.Accept();
                byte[] receivedData = receiver.Receive();

                localConnection.Close();

                Assert.IsTrue(expectedData.SequenceEqual(receivedData), "Data are not the same.");
            }
            else
                Assert.Fail("Can't obtain IP address.");
        }

        [TestMethod()]
        public void Receive_More_Then_ReceiveBufferSize_Bytes_Test()
        {
            IPAddress ipAddress = Util.GetLocalIPAddress();
            if (ipAddress != null)
            {
                IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 11000);

                Connection localConnection = new Connection();
                localConnection.Listen(localEndPoint);

                byte[] expectedData = new byte[localConnection.ReadBufferSize + 1];
                for (int i = 0; i < expectedData.Length; i++)
                    expectedData[i] = (byte)(i % byte.MaxValue);
                new Task(() =>
                {
                    Socket remoteSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                    System.Threading.Thread.Sleep(1000);
                    remoteSocket.Connect(localEndPoint);
                    remoteSocket.Send(expectedData);
                }).Start();

                var receiver = localConnection.Accept();
                byte[] receivedData = receiver.Receive();

                localConnection.Close();

                Assert.IsTrue(expectedData.SequenceEqual(receivedData), "Data are not the same.");
            }
            else
                Assert.Fail("Can't obtain IP address.");
        }

        [TestMethod()]
        public void Receive_Exactly_ReceiveBufferSize_Bytes_Test()
        {
            IPAddress ipAddress = Util.GetLocalIPAddress();
            if (ipAddress != null)
            {
                IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 11000);

                Connection localConnection = new Connection();
                localConnection.Listen(localEndPoint);

                byte[] expectedData = new byte[localConnection.ReadBufferSize];
                for (int i = 0; i < expectedData.Length; i++)
                    expectedData[i] = (byte)(i % byte.MaxValue);

                new Task(() =>
                {
                    Socket sender = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                    System.Threading.Thread.Sleep(1000);
                    sender.Connect(localEndPoint);
                    sender.Send(expectedData);
                }).Start();

                var remoteConnection = localConnection.Accept();
                byte[] receivedData = remoteConnection.Receive();

                localConnection.Close();

                Assert.IsTrue(expectedData.SequenceEqual(receivedData), "Data are not the same.");
            }
            else
                Assert.Fail("Can't obtain IP address.");
        }
    }
}