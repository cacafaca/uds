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
        private IPEndPoint _From;

        public IPEndPoint From
        {
            get { return _From; }
            set { _From = value; }
        }

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

    }

    public enum MessageType
    {
        Unknown = -1,
        System = 0,
        Custom = 1
    }

}
