using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Uds.Communication
{
    public class Message
    {
        [NonSerialized]
        private IPEndPoint _From;

        public IPEndPoint From
        {
            get { return _From; }
            set { _From = value; }
        }

        [NonSerialized]
        private IPEndPoint _To;

        public IPEndPoint To
        {
            get { return _To; }
            set { _To = value; }
        }

        private object _Body;

        public object Body
        {
            get { return _Body; }
            set { _Body = value; }
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
            return GetHashCode();
        }

        public Message()
        {

        }
    }

    public enum MessageType
    {
        Unknown = -1,
        System = 0,
        Custom = 1
    }

}
