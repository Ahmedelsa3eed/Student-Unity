/* PLEASE DO NOT COPY AND PASTE THIS CODE. */ (function () {
    var w = window,
        C = '___grecaptcha_cfg',
        cfg = (w[C] = w[C] || {}),
        N = 'grecaptcha';
    var gr = (w[N] = w[N] || {});
    gr.ready =
        gr.ready ||
        function (f) {
            (cfg['fns'] = cfg['fns'] || []).push(f);
        };
    w['__recaptcha_api'] = 'https://www.google.com/recaptcha/api2/';
    (cfg['render'] = cfg['render'] || []).push('onload');
    w['__google_recaptcha_client'] = true;
    var d = document,
        po = d.createElement('script');
    po.type = 'text/javascript';
    po.async = true;
    po.src = 'https://www.gstatic.com/recaptcha/releases/5qcenVbrhOy8zihcc2aHOWD4/recaptcha__en.js';
    po.crossOrigin = 'anonymous';
    po.integrity = 'sha384-0UmWi2drUOFtiIl9tXA3B9fWn6Oz5K3vecq0zC8vAzesBQkPzPdlJKXnimDbxieW';
    var e = d.querySelector('script[nonce]'),
        n = e && (e['nonce'] || e.getAttribute('nonce'));
    if (n) {
        po.setAttribute('nonce', n);
    }
    var s = d.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(po, s);
})();

var siteKey = '6LfcyrAjAAAAAEd-mqSDGohaU78Zc2ag3t5494Ad';
