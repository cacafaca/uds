using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;
using System.Runtime.Serialization;
using System.Xml;
using System.Xml.Schema;

namespace Uds.Communication
{
    [Serializable]
    [XmlRoot]
    public class Message : IXmlSerializable 
    {
        private byte[] _FromAddress;
        private int _FromPort;

        [XmlIgnore]
        public IPEndPoint From
        {
            get
            {
                return new IPEndPoint(new IPAddress(_FromAddress), _FromPort);
            }
            set
            {
                _FromAddress = value.Address.GetAddressBytes();
                _FromPort = value.Port;
            }
        }

        private byte[] _ToAddress;
        private int _ToPort;

        [XmlIgnore]
        public IPEndPoint To
        {
            get
            {
                return new IPEndPoint(new IPAddress(_ToAddress), _ToPort);
            }
            set
            {
                _ToAddress = value.Address.GetAddressBytes();
                _ToPort = value.Port;
            }
        }

        private object _Body;

        [XmlElement]
        public object Body
        {
            get { return _Body; }
            set
            {
                if (value.GetType().IsDefined(typeof(SerializableAttribute), false))
                    _Body = value;
                else
                    throw new ArgumentException("Body is not serializable.");
            }
        }

        private long _Id;

        public long Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private MessageType _MessageType;

        public MessageType MessageType
        {
            get { return _MessageType; }
            set { _MessageType = value; }
        }

        public override bool Equals(System.Object obj)
        {
            // If parameter is null return false.
            if (obj == null)
            {
                return false;
            }

            // If parameter cannot be cast to Point return false.
            Message p = obj as Message;
            if ((System.Object)p == null)
            {
                return false;
            }

            // Return true if the fields match:
            return Equals(p);
        }

        public bool Equals(Message p)
        {
            // If parameter is null return false:
            if ((object)p == null)
            {
                return false;
            }

            return From.Equals(p.From) &&
                To.Equals(p.To) &&
                Body.Equals(p.Body) &&
                Id.Equals(p.Id) &&
                MessageType.Equals(p.MessageType);
        }

        public override int GetHashCode()
        {
            return new { From, To, Id, Body, MessageType }.GetHashCode();
        }

        public XmlSchema GetSchema()
        {
            return null;
        }

        public void ReadXml(XmlReader reader)
        {
            reader.MoveToContent();

            bool emptyElement = reader.IsEmptyElement;
            reader.ReadStartElement();
            if (!emptyElement)
            {
                string tempValueString;
                IPAddress tempIpAddress;
                int tempInt;

                tempValueString = reader.ReadElementContentAsString(nameof(_FromAddress).Substring(1), string.Empty);
                if (IPAddress.TryParse(tempValueString, out tempIpAddress))
                    _FromAddress = tempIpAddress.GetAddressBytes();
                else
                    _FromAddress = new byte[4];

                tempValueString = reader.ReadElementContentAsString(nameof(_FromPort).Substring(1), string.Empty);
                if (int.TryParse(tempValueString, out tempInt))
                    _FromPort = tempInt;
                else
                    _FromPort = 0;

                tempValueString = reader.ReadElementContentAsString(nameof(_ToAddress).Substring(1), string.Empty);
                if (IPAddress.TryParse(tempValueString, out tempIpAddress))
                    _ToAddress = tempIpAddress.GetAddressBytes();
                else
                    _ToAddress = new byte[4];

                tempValueString = reader.ReadElementContentAsString(nameof(_ToPort).Substring(1), string.Empty);
                if (int.TryParse(tempValueString, out tempInt))
                    _ToPort = tempInt;
                else
                    _ToPort = 0;

                string bodyAsString = reader.ReadElementContentAsString(nameof(Body), string.Empty);

                _Id = reader.ReadElementContentAsInt(nameof(Id), string.Empty);

                _MessageType = (MessageType)reader.ReadElementContentAsInt(nameof(MessageType), string.Empty);
            }


        }

        private string ByteAddressToString(byte[] address)
        {
            if (address != null)
                return (new IPAddress(address)).ToString();
            else
                return string.Empty;
        }

        public void WriteXml(XmlWriter writer)
        {
            writer.WriteElementString(nameof(_FromAddress).Substring(1), ByteAddressToString(_FromAddress));
            writer.WriteElementString(nameof(_FromPort).Substring(1), _FromPort.ToString());
            writer.WriteElementString(nameof(_ToAddress).Substring(1), ByteAddressToString(_ToAddress));
            writer.WriteElementString(nameof(_ToPort).Substring(1), _ToPort.ToString());

            //writer.WriteElementString(nameof(Body), _Body.ToString());
            var serializedBody = Uds.Common.Serialize.SerializeToXmlStream(_Body);
            writer.WriteStartElement(nameof(Body));
            writer.WriteRaw((new System.IO.StreamReader(serializedBody)).ReadToEnd());
            writer.WriteEndElement();

            writer.WriteElementString(nameof(Id), _Id.ToString());
            writer.WriteElementString(nameof(MessageType), ((int)_MessageType).ToString());
        }

        public Message()
        {
            _FromAddress = new byte[4];
            _ToAddress = new byte[4];
        }
    }

    public enum MessageType
    {
        Unknown = -1,
        System = 0,
        Custom = 1
    }

}
