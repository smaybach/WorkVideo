package my1508d.myyuekao.presenter;

import my1508d.myyuekao.bean.Bean;
import my1508d.myyuekao.bean.Xiang;
import my1508d.myyuekao.model.IModelMain;
import my1508d.myyuekao.model.ModelMain;
import my1508d.myyuekao.view.IViewModel;

/**
 * Created by wangguojian on 2017/12/13.
 */

public class PresenterMain {

    public IViewModel viewModel;
    public ModelMain modelMain;

    public PresenterMain(IViewModel viewModel) {
        this.viewModel = viewModel;
        this.modelMain = new ModelMain();
    }
    public void getData(){
        modelMain.getData(new IModelMain() {
            @Override
            public void onBean(Bean bean) {
                if(viewModel != null){
                    viewModel.onBean(bean);
                }
//           view.onBean(bean);
            }

            @Override
            public void onVideo(Xiang video) {

            }
        });
    }
    public void getVideo(String urlID){
        modelMain.getVideo(new IModelMain() {
            @Override
            public void onBean(Bean bean) {

            }

            @Override
            public void onVideo(Xiang video) {
                if(viewModel != null){
                    viewModel.onVideo(video);
                }
//                view.onVideo(video);
            }
        },urlID);
    }



}
