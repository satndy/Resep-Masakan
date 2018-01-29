package com.arissandy.satria.newresepmasakan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arissandy.satria.newresepmasakan.models.Masakan;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.welcome_side1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
                dataMasakan();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                    dataMasakan();
                }
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, Main2Activity.class));
        finish();
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void dataMasakan() {
        Masakan masakan = new Masakan("Ayam Geprek","ayam",
                "-3 potong ayam bagian dada di filet\n" +
                        "-1 bungkus tepung terigu sajiku\n" +
                        "-1 buah jeruk nipis\n" +
                        "-secukupnya Air dingin\n" +
                        "-secukupnya Minyak goreng\n" +
                        "-Bahan sambel :\n" +
                        "-10 biji cabe rawit\n" +
                        "-5 biji bawang merah\n" +
                        "-3 biji bawang putih\n" +
                        "-1 buah tomat\n" +
                        "-secukupnya Garam, gula, merica, lada, penyedap\n" +
                        "-2 sendok minyak goreng ",
                "-Bersihkan ayam, lalu potong” sesuai selera, kemudian rendam dengan perasan jeruk nipis, sekitar -+ 1 jam.\n" +
                        "\n" +
                        "-Campur semua bahan sambel lalu uleg/haluskan bahan tersebut (tanpa minyak).\n" +
                        "\n" +
                        "-Setelah itu sambel yang tadi tambahkan dengan 2 sdk minyak goreng, lalu tumis sebentar saja.\n" +
                        "\n" +
                        "-Buat adonan basah : ambil secukupnya tepung sajiku + air dingin, hingga sedikit kental.\n" +
                        "\n" +
                        "-Adonan kering : tepung sajiku taruh di wadah.\n" +
                        "\n" +
                        "-Ambil 1 bagian ayam, balur ke adonan kering, lalu adonan basah, lalu adonan kering. Begitu terus sampai ayamnya habis.\n" +
                        "\n" +
                        "-Goreng ayam tersebut hingga warna coklat kekuningan, dan ayam siap diangkat.\n" +
                        "\n" +
                        "-Setelah semua siap, taruh lahh dipiring tata sesuai kreasi kamu. Selamat mencoba",Integer.toString(R.drawable.salad),0);
        masakan.save();

        Masakan masakan1 = new Masakan("Ayam Goreng Mentega","ayam",
                "-1 ekor ayam dipotong2 \n" +
                        "-3 siung bawang putih cincang \n" +
                        "-1 bawang bombay iris kasar \n" +
                        "-2 sdm kecap inggris \n" +
                        "-1 sdt saos tiram \n" +
                        "-1 sdm kecap manis \n" +
                        "-1 sdt kecap asin \n" +
                        "-secukupnya Garam, gula, merica \n" +
                        "-2 sdm mentega \n" +
                        "-Bumbu marinasi: \n" +
                        "-3 siung bawang putih dihaluskan \n" +
                        "-secukupnya Garam, merica ",
                "-Campur ayam dengan bumbu marinasi. Diamkan selama 1 jam. Goreng ayam. Angkat. Tiriskan \n" +
                        "-Panaskan wajan, masukkan 2 sdm mentega dan 1 sdm minyak goreng. Tambahkan bawang putih dan bawang bombay tumis hingga harum \n" +
                        "-Masukkan ayam goreng lalu tambahkan kecap manis, kecap asin, kecap inggris, saos tiram, garam, gula dan sedikit air. Aduk rata dan biarkan hingga mendidih. Sajikan ",Integer.toString(R.drawable.salad),0);
        masakan1.save();

        Masakan masakan2 = new Masakan("Ayam Kalasan","ayam",
                "-1 ekor ayam pejantan \n" +
                        "-700 ml air kelapa \n" +
                        "-4 siung bawang putih \n" +
                        "-4 siung bawang merah \n" +
                        "-3 cm kunyit \n" +
                        "-2 cm jahe \n" +
                        "-2 cm laos \n" +
                        "-3 lembar daun salam \n" +
                        "-2 lembar daun jeruk \n" +
                        "-1 batang sereh \n" +
                        "-1,5 sdm garam (sesuai selera)  ",
                "-Uleg semua bumbu jadi satu. Bawput, bawme,kunyit, jahe, laos, garam.\n" +
                        "-Cuci bersih ayam. Lalu panaskan wajan. Tuang air kelapa dan bumbu kemudian aduk rata. Lalu masukkan ayam dan masak kurleb 45 menit dengan api kecil.\n" +
                        "-Diamkan minimal 2 jam.\n" +
                        "-Setelah itu goreng diatas minyak panas yang banyak.\n" +
                        "-Ayam kalasan siap di sajikan",Integer.toString(R.drawable.salad),0);
        masakan2.save();

        Masakan masakan3 = new Masakan("Ayam Lada Hitam","ayam",
                "-300 gr dada ayam (potong dadu)\n" +
                        "-Bahan Marinade :\n" +
                        "-2 sdm saos tiram\n" +
                        "-1 sdm kecap manis\n" +
                        "-secukupnya Gula garam lada\n" +
                        "-Untuk menumis :\n" +
                        "-1 paprika (potong sesuai selera)\n" +
                        "-2 siung bawang putih (cincang)\n" +
                        "-1 ruas jari Jahe\n" +
                        "-Lada hitam\n" +
                        "-50 ml air\n" +
                        "-secukupnya Minyak ",
                "-Campurkan ayam dan bahan marinade, aduk rata. Diamkan kurang lebih 30 menit\n" +
                        "\n" +
                        "-Panaskan minyak, masukan bawang putih dan jahe hingga harum, lalu masukan paprika.\n" +
                        "\n" +
                        "-Masukan ayam, aduk2. masukan air dan lada hitam\n" +
                        "\n" +
                        "-Aduk hingga air sedikit menyusut. Koreksi rasa, dan selesai.",Integer.toString(R.drawable.salad),1);
        masakan3.save();

        Masakan masakan4 = new Masakan("Opor Ayam Tahu","ayam",
                "-4 potong sayap \n" +
                        "-4 tahu goreng (tahu kulit) \n" +
                        "-1/2 buah jeruk nipis \n" +
                        "-Bumbu halus \n" +
                        "-7 siung bamer (2 siung shallot uk sedang) \n" +
                        "-5 siung baput \n" +
                        "-2 cm jahe  \n" +
                        "-1/2 sdt lada bubuk \n" +
                        "-1/2 sdt jinten bubuk \n" +
                        "-1/2 sdt kunyit bubuk (ga punya kunyit) \n" +
                        "-2 butir kemiri\n" +
                        "-Rempah\n" +
                        "-1 batang sere yg gedhe\n" +
                        "-1 ruas lengkuas (galangal)\n" +
                        "-1 buah star anise(bunga pekak) aku beli di tesco\n" +
                        "-3 butir cengkeh (aku pake yg di tesco)\n" +
                        "-1/2 sdt ground cinamon (kayu manis)\n" +
                        "-3 lembar daun salam (bay leaves)\n" +
                        "-2 lembar daun jeruk\n" +
                        "-Gula pasir\n" +
                        "-Gula merah (bisa di skip)\n" +
                        "-Garam\n" +
                        "-200 cc Santan (aku pake kalengan merk aldi)\n" +
                        "-500 cc air\n" +
                        "-Minyak goreng (aku pake olive oil)\n" +
                        "-1/2 sdm ketumbar bubuk",
                "-Cuci bersih ayam dan rendam dengan air jeruk nipis sekitar 10 menit, kemudian cuci bersih\n" +
                        "-Haluskan bumbu halus, tumis dengan minyak panas sampai matang dan masukkan rempah (lengkuas, kayu manis, sere, daun salam, daun jeruk, cengkeh, star anise)\n" +
                        "-Masukkan ayam dan tahu kulit, tambahkan air. Masak sampai ayam matang\n" +
                        "-Tambahkan gula, garam, gula merah. Koreksi rasa, tunggu sampai meresap\n" +
                        "-Setelah kuah meresap dan ayam matang, tambahkan santen\n" +
                        "-Aduk2 sampai mendidih agar santan tidak pecah\n" +
                        "-Koreksi rasa, sajikan dengan bawang goreng untuk tambahan",Integer.toString(R.drawable.salad),0);
        masakan4.save();

        Masakan masakan5 = new Masakan("Daging Lada Hitam","daging",
                "-300 gr daging sapi\n" +
                        "-1 sdt lada hitam\n" +
                        "-1 sdm saus tiram\n" +
                        "-2 sdm kecap manis\n" +
                        "-3 bh cabai merah iris memanjang \n" +
                        "-secukupnya Garam\n" +
                        "-50 ml air\n" +
                        "-Bumbu halus :\n" +
                        "-2 siung bawang putih\n" +
                        "-3 siung bawang merah ",
                "-Tumis bumbu halus hingga harum.lalu masulan cabai,tumis hingga layu.\n" +
                        "-Setelah itu masukan daging sapi,tambahkan sdkt air.\n" +
                        "-Lalu tambahkan kecap,saus tiram,lada hitam dan garam.tes rasa. Setelah bumbu meresap dan air menyusut,matikan kompor\n" +
                        "-Daging lada hitam siap disajikan.",Integer.toString(R.drawable.salad),0);
        masakan5.save();

        Masakan masakan6 = new Masakan("Daging Balado","daging",
                "-500 gr Daging sapi\n" +
                        "-1 buah kentang\n" +
                        "-1 buah Tomat\n" +
                        "-15 Cabai merah\n" +
                        "-1 Bawang putih\n" +
                        "-1 Bawang merah\n" +
                        "-Garam\n" +
                        "-Minyak goreng",
                "-Cuci daging sapi dengan air 3 kali (yg ketiga kali air dialirkan ke daging)\n" +
                        "-Rebus daging sapi sampai empuk, dinginkan.\n" +
                        "-Setelah dingin, iris daging sapi (jangan terlalu tipis) kemudian goreng sebentar saja. Jangan sampai dagingnya keras.\n" +
                        "-Potong dadu kentang, balur dg sedikit garam. Goreng.\n" +
                        "-Rebus bahan sambal balado, tiriskan jangan buang airnya. Ulek.\n" +
                        "-Tumis sambal balado yang sudah diulek masukkan sedikit air sisa rebusan td. Tambahkan garam, koreksi rasa.\n" +
                        "-Masukkan daging sapi dan kentang goreng tadi ke dalam sambal yang sudah masak. Aduk.\n" +
                        "-Daging sapi balado siap disajikan. Selamat menikmati",Integer.toString(R.drawable.salad),0);
        masakan6.save();

        Masakan masakan7 = new Masakan("Krengsengan Daging","daging",
                "-150 ons daging sapi\n" +
                        "-1 siung bawang putih geprek\n" +
                        "-2 cm jahe geprek\n" +
                        "-3 sdm kecap manis\n" +
                        "-1 sdm petis\n" +
                        "-Secukupnya gula\n" +
                        "-Secukupnya garam\n" +
                        "-Secukupnya penyedap\n" +
                        "-bumbu halus:\n" +
                        "-6 siung bawang merah\n" +
                        "-2 siung bawang putih\n" +
                        "-1 buah cabe merah besar\n" +
                        "-5 buah cabe rawit\n" +
                        "-1 sdt merica hitam\n" +
                        "-3 butir kemiri",
                "-Potong daging sapi sesuai selera. Jangan terlalu tebal. Rebus bersama jahe dan bawang putih geprek\n" +
                        "-Haluskan bumbu. Tumis bumbu hingga benar2 matang lalu masukkan daging. Aduk rata. Tambahkan air kaldu hingga daging terendam.\n" +
                        "-Beri kecap manis, petis, gula, garam dan penyedap. Tes rasa. Tutup daging dan biarkan hingga air surut.\n" +
                        "-Angkat, taburi bawang goreng diatasnya dan sajikan.",Integer.toString(R.drawable.salad),0);
        masakan7.save();

        Masakan masakan8 = new Masakan("Gurame Asam Manis","ikan",
                "-Ikan Gurame yang sudah di goreng\n" +
                        "-1 Bawang Bombay\n" +
                        "-1 Tomat potong dadu\n" +
                        "-2 sdm Olive oil\n" +
                        "-2 sdm Kecap Inggris\n" +
                        "-2 siung Bawang putih\n" +
                        "-5 sdm Saos Tomat\n" +
                        "-3 sdm Saos Sambal\n" +
                        "-1 sdt Lada\n" +
                        "-1/2 sdt Garam\n" +
                        "-1 sdt Gula\n" +
                        "-1 ruas jari Jahe\n" +
                        "-50 ml Air ",
                "-Tumis bawang putih, jahe, dan bawang bombay hingga harum\n" +
                        "-Masukkan tomat tunggu hingga layu\n" +
                        "-Campurkan saos tomat, saos sambal, kecap inggris, lada, garam dan gula aduk hingga rata\n" +
                        "-Tambahkan air hingga mendidih",Integer.toString(R.drawable.salad),0);
        masakan8.save();

        Masakan masakan9 = new Masakan("Gurame Goreng Tepung","ikan",
                "-1 Ekor Ikan Gurame (Fillet)\n" +
                        "-1 buah jeruk Nipis\n" +
                        "-secukupnya Garam\n" +
                        "-1 Bungkus kecil Tepung Bumbu (Sajiku atau Kobe)\n" +
                        "-2 sdm Tepung Terigu\n" +
                        "-2 sdm Tepung Beras\n" +
                        "-1 Butir Telur Ayam",
                "-Iris ikan gurame kemudian rendam dengan perasan jeruk nipis dan di beri garam, sisihkan supaya meresap.\n" +
                        "-Campur semua tepung aduk rata. Sisihkan\n" +
                        "-Kocok telur, masukan irisan ikan kedalam telur lalu masukan kedalam campuran tepung, lakukan berulang, ketebalan sesuai selera.\n" +
                        "-Goreng dalam minyak panas dengan api sedang, supaya ikan matang merata.\n" +
                        "-Selamat mencoba",Integer.toString(R.drawable.salad),0);
        masakan9.save();

        Masakan masakan10 = new Masakan("Lele Balado","ikan",
                "-1/2 kg ikan lele(cuci bersih)\n" +
                        "-3 buah cabai merah\n" +
                        "-5 buah cabai rawit(bisa dikurangin yang gk suka pedes)\n" +
                        "-3 butir Bawang putih\n" +
                        "-2 butir bawang merah\n" +
                        "-2 buah tomat (uk.sedang)\n" +
                        "-secukupnya Gula jawa,penyedap rsa,garam\n" +
                        "-Minyak secukupnya (untuk mengoreng lele dan tumis bumbu)\n" +
                        "-secukupnya Air\n",
                "-Goreng ikan lele sampai mateng\n" +
                        "-Haluskan cabai,bawang merah,bawang putih,tomat,kmudian tumis bumbu hingga kecium bau sedap trus masukan lele\n" +
                        "-Tambahkan air secukupnya dan tambah penyedap rasa,gula jawa,garam.masak hingga matang.",Integer.toString(R.drawable.salad),0);
        masakan10.save();

        Masakan masakan11 = new Masakan("Martabak Mie","mie",
                "-2 bungkus mi goreng instan\n" +
                        "-5 butir telur\n" +
                        "-2 batang daun bawang\n" +
                        "-3 siung bawang merah\n" +
                        "-1 bgn bawang putih\n" +
                        "-1 sdm cabe giling\n",
                "-Rebus mi goreng, tiriskan\n" +
                        "-Iris daun bawang, bawang putih dan bawang merah, letakkan dlm suatu wadah\n" +
                        "-Tambahkan cabe giling dan telur satu per satu dlm wadah td\n" +
                        "-Masukkan bumbu mi goreng, aduk rata\n" +
                        "-Panaskan wajan, beri minyak goreng secukupnya\n" +
                        "-Tuang adonan ke dlm wajan\n" +
                        "-Balik adonan, tunggu sampai matang, angkat\n" +
                        "-Selamat menikmati, bisa dinikmati dgn sambal",Integer.toString(R.drawable.salad),0);
        masakan11.save();

        Masakan masakan12 = new Masakan("Mie Jamur","mie",
                "-Mi pangsit\n" +
                        "-Sawi \n" +
                        "-100 gr jamur kancing\n" +
                        "-1 buah bawang bombay ukuran sedang (potong-potong)\n" +
                        "-3 siung bawang putih\n" +
                        "-2 butir Kemiri\n" +
                        "-1 buah tomat sedang\n" +
                        "-Kecap\n" +
                        "-Garam, gula, lada\n" +
                        "-100 ml air\n" +
                        "-Taburan:\n" +
                        "-Bawang goreng dan daun bawang",
                "-Rebus sawi terlebih dahulu, hingga setengah matang. Masukkan mi pangsit. Angkat dan tiriskan. Selagi masih panas, beri sedikit minyak sayur (agar mi tidak lengket), lada, garam. Aduk rata. Sisihkan.\n" +
                        "-Haluskan bumbu: bawang putih, kemiri, dan tomat. Tumis bumbu dan bawang bombay. Setelah II jamur matang, dan air mulai menyusut.\n" +
                        "-Mi pangsit dan jamur kecap siap dihidangkan. Tambahkan taburan bawang goreng dan daun bawang.",Integer.toString(R.drawable.salad),0);
        masakan12.save();

        Masakan masakan13 = new Masakan("Mie Ayam Lada Hitam","mie",
                "-bumbu halus :\n" +
                        "-6 siung bawang merah\n" +
                        "-6 siung bawang putih\n" +
                        "-sejempol jahe \n" +
                        "-bahan tambahan\n" +
                        "-1 bungkus mi telor (saya 3 dara)\n" +
                        "-Ayam (dadanya)\n" +
                        "-crab stick,bakso,jamur(optional)\n" +
                        "-1 bungkus pakcoy (tidak dipakai semua)\n" +
                        "-bumbu tambahan :\n" +
                        "-Saori saus tiram\n" +
                        "-Lada hitam(saya cuma punya yang bubuk)\n" +
                        "-Secukupnya kecap manis\n" +
                        "-secukupnya gula & garam\n" +
                        "-royco(optional)\n" +
                        "-bumbu pelengkap 2 :\n" +
                        "-kerupuk (saya lupa)\n" +
                        "-3 siung bawang goreng (saya bawang merah diiris lalu digoreng)\n" +
                        "-cabe rawit + jeruk nipis ",
                "-Giling bumbu halus\n" +
                        "-Tumis lalu masukkan ayam(dada) yang sudah direbus terlebih dahulu tambahkan saori,kecap,lada hitam(agak banyak),gula,garam aduk sampai benar2 mengental lalu setelah matang sisihkan\n" +
                        "-Rebus mie telur setelah matang sisihkan lanjutkan merebus crabstick,bakso,jamur kancing\n" +
                        "-Kuah : air kaldu rebusan ayam tadi + royco sedikit sampai terasa\n" +
                        "-Rebus pakcoy (kalau suka kuah yang keruh masukan pakcoynya kedalam kuah)",Integer.toString(R.drawable.salad),0);
        masakan13.save();

        Masakan masakan14 = new Masakan("Bandrek","minuman",
                "-1.000 ml air\n" +
                        "-200 g gula jawa, sisir halus\n" +
                        "-100 g jahe bakar, memarkan\n" +
                        "-5 cm kayu manis\n" +
                        "-5 btr cengkeh\n" +
                        "-1/2 sdt garam ",
                "-Rebus air dan bersama semua bahan hingga mendidih dan beraroma.\n" +
                        "-Saring dan sajikan hangat.",Integer.toString(R.drawable.salad),0);
        masakan14.save();

        Masakan masakan15 = new Masakan("Wedang Ronde","minuman",
                "-1.000 ml air\n" +
                        "-200 g gula jawa, sisir halus\n" +
                        "-100 g jahe bakar, memarkan\n" +
                        "-5 cm kayu manis\n" +
                        "-5 btr cengkeh\n" +
                        "-1/2 sdt garam ",
                "-Rebus air dan bersama semua bahan hingga mendidih dan beraroma.\n" +
                        "-Saring dan sajikan hangat.",Integer.toString(R.drawable.salad),0);
        masakan15.save();

        Masakan masakan16 = new Masakan("Es Sekoteng","minuman",
                "-Secukupnya buah alpukat, dikeruk\n" +
                        "-Secukpnya kelapa muda beserta airnya\n" +
                        "-Secukupnya pacar cina merah\n" +
                        "-Secukupnya air gula\n" +
                        "-Secukupnya susu kental manis putih\n" +
                        "-Secukupnya es batu",
                "-Tuangkan air kelapa ke mangkok saji, tambahkan air putih biasa, tambahkan air gula, dan tambahkan susu kental manis. Aduk.\n" +
                        "-Masukkan alpukan yg telah dikeruk, pacar cina merah, dan daging kelapa muda.\n" +
                        "-Taburkan es batu, dan beri sedikit susu kental manis agar terlihat lebih segar.",Integer.toString(R.drawable.salad),0);
        masakan16.save();

        Masakan masakan17 = new Masakan("Cendol","minuman",
                "-250 gram tepung beras disangrai dengan daun pandan\n" +
                        "-2 liter air putih\n" +
                        "-1 sdt garam",
                "-Campur tepung beras, garam dengan air sebagian sampai tercampur rata dan lembut\n" +
                        "-Sisa air di rebus sampai mendidih ya..kemudian masukkan campuran tepung beras cair tadi sampai kental cukup buat di bentuk\n" +
                        "-Setelah matang, siapkan air dalam baskom atau tempat bersih lainnya\n" +
                        "-Kemudian saring cendol dengan saringan cendol (aku pakai saringan magic xom, seadanya) ke dalam air yang sudah disediakan",Integer.toString(R.drawable.salad),0);
        masakan17.save();

        Masakan masakan18 = new Masakan("Beras Kencur","minuman",
                "-150 gr beras\n" +
                        "-250 gr kencur\n" +
                        "-200 gr gula merah\n" +
                        "-50 gr gula putih\n" +
                        "-1/2 sdm garam\n" +
                        "-1 sdm asam jawa\n" +
                        "-2 butir jeruk nipis\n" +
                        "-1 ruas jari jahe\n" +
                        "-2 lt air ",
                "-Rendam beras semalaman\n" +
                        "-Sangrai beras hingga berubah warna\n" +
                        "-Bakar jahe dan kencur kemudian dicuci bersih dan di geprek\n" +
                        "-Rebus air, gula, garam, asam, kencur dan jahe yg telah dibakar\n" +
                        "-Setelah dingin blender kencur dan jahe. Jangan lupa beras yg telah disangrai diikutsertakan\n" +
                        "-Campur dgn rebusan air tadi dan saring....\n" +
                        "-Jika akan digunakan lbh lama setelah disaring bisa direbus kembali sebentar\n" +
                        "-Setiap akan dihidangkan ditambahkan perasan jeruk nipis 2 sendok.",Integer.toString(R.drawable.salad),0);
        masakan18.save();




    }
}
